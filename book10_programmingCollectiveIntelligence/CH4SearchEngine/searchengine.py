# -*- coding: utf-8 -*-

# 选用archlinux的wiki为靶

import time
import requests
import sqlite3
import re
from bs4 import BeautifulSoup

# words to ignore
ignorewords=set(['the','of','to','and','a','in','is','it'])

class crawler:
    # Initialize the crawler with the name of database
    def __init__(self,dbname):
        self.con = sqlite3.connect(dbname)
    
    def __del__(self):
        print("Good bye, ")
        self.con.close()
        print("the fantasy world")
    
    def dbcommit(self):
        self.con.commit()
    
    # Auxilliary function for getting an entry id and adding
    # it if it's not present
    def getentryid(self,table,field,value,createnew=True):
        cur=self.con.execute(
        "select rowid from %s where %s='%s'" % (table,field,value))
        res=cur.fetchone()
        if res==None:
            cur=self.con.execute(
            "insert into %s (%s) values ('%s')" % (table,field,value))
            return cur.lastrowid
        else:
            return res[0]
    
    # Index an individual page
    def addtoindex(self,url,soup):
        if self.isindexed(url): return
        print('Indexing '+url)
        
        text=self.gettextonly(soup) #拿到页面上的文本
        words=self.separatewords(text) #分词结果
        
        urlid=self.getentryid('urllist','url',url) #获取id
        
        # 每个word，在那个url指向的文档中，出现的位置
        for i in range(len(words)):
            word=words[i]
            if word in ignorewords: continue
            wordid=self.getentryid('wordlist','word',word)
            self.con.execute("insert into wordlocation(urlid,wordid,location) \
            values (%d,%d,%d)" % (urlid,wordid,i))
    
    # 提取不带html标签的文本内容
    def gettextonly(self,soup):
        return soup.get_text()
        
    # 所有非字母、非数字的字符，都被当做分隔符，以此分词
    def separatewords(self,text):
        splitter=re.compile('\\W*') #大小的W表示的意思与小写的w相反
        '''
        \w等价于[a-zA-Z0-9_]，\W等价于[^a-zA-Z0-9_]，就是排除了\w的情况
        '''
        return [s.lower( ) for s in splitter.split(text) if s!='']
        
    # Return true if this url is already indexed
    def isindexed(self,url):
        print("debug:"+url)
        u=self.con.execute \
            ("select rowid from urllist where url=\"%s\"" % url).fetchone()
        if u!=None:
            # Check if it has actually been crawled
            v=self.con.execute(
            'select * from wordlocation where urlid=%d' % u[0]).fetchone()
            if v!=None: return True
        return False
        
    # Add a link between two pages
    def addlinkref(self,urlFrom,urlTo,linkText):
        pass
    
    # Starting with a list of pages, do a breadth
    # first search to the given depth, indexing pages
    # as we go
    def crawl(self,pages,depth=2):
        for i in range(depth):
            newpages=set()
            for page in pages:
                time.sleep(2) #避免访问太频繁
                try:
                    c=requests.get(page)
                except:
                    print("Could not open %s" % page)
                    continue
            
                soup=BeautifulSoup(c.text,'lxml')
                self.addtoindex(page,soup)
                
                links=soup.find_all('a') #提取所有a标签

                for link in links:
                    url = link.get('href')
                    if(url):
                        if(url[0:4]=='http'):#如果是普通链接
                            url = url # 不用动
                        elif(url[0:1]=='/'):
                            url = 'https://wiki.archlinux.org' + url
                        else:#忽略一些其他情况，如页内跳转的锚点(#)或javascript:;等
                            continue
                        
                        url=url.split("#")[0]
                        url=url.split("?")[0]
                        if(url and (not self.isindexed(url))):
                            newpages.add(url)
                        else:
                            continue
                    # 以上提取页面上的超链接

                    linkText=self.gettextonly(link)
                    self.addlinkref(page,url,linkText)

                self.dbcommit()
            
            pages=newpages
    
    # Create the database tables，在sqlite里建的每个表都自带rowid，不用再额外加id
    # 可以 select rowid,* from urllist 来试看。另外，如不指定的话，字段的数据类型默认为object
    def createindextables(self):
        self.con.execute('create table if not exists urllist(url)') #存已经过索引的URL
        self.con.execute('create table if not exists wordlist(word)') #出现过的单词
        self.con.execute('create table if not exists wordlocation(urlid,wordid,location)')
        #上表用list存单词在文档中所处的位置↑，下表用两个rowid存与其相应的两个url之间的跳转关系↓
        self.con.execute('create table if not exists link(fromid integer,toid integer)')
        #下表存某个word用在某个link指向的文档中的记录
        self.con.execute('create table if not exists linkwords(wordid,linkid)')
        #再下是给所有表创建索引
        self.con.execute('create index if not exists wordidx on wordlist(word)')
        self.con.execute('create index if not exists urlidx on urllist(url)')
        self.con.execute('create index if not exists wordurlidx on wordlocation(wordid)')
        self.con.execute('create index if not exists urltoidx on link(toid)')
        self.con.execute('create index if not exists urlfromidx on link(fromid)')
        self.dbcommit( )


crawler=crawler('test.db')
crawler.createindextables()
pagelist=['https://wiki.archlinux.org/index.php/Installation_guide']
crawler.crawl(pagelist)

#del crawler