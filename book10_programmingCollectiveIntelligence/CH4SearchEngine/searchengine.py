# -*- coding: utf-8 -*-

# 选用人民网英文版为靶

import time
import requests
import sqlite3
from bs4 import BeautifulSoup

# words to ignore
ignorewords=set(['the','of','to','and','a','in','is','it'])

class crawler:
    # Initialize the crawler with the name of database
    def __init__(self,dbname):
        self.con = sqlite3.connect(dbname)
    
    def __del__(self):
        self.con.close()
    
    def dbcommit(self):
        self.con.commit()
    
    # Auxilliary function for getting an entry id and adding
    # it if it's not present
    def getentryid(self,table,field,value,createnew=True):
        return None
    
    # Index an individual page
    def addtoindex(self,url,soup):
        print('Indexing %s' % url)
    
    # Extract the text from an HTML page (no tags)
    def gettextonly(self,soup):
        return None
        
    # Separate the words by any non-whitespace character
    def separatewords(self,text):
        return None
        
    # Return true if this url is already indexed
    def isindexed(self,url):
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
                        if(url[0:20]=='http://en.people.cn/' and not self.isindexed(url)):
                            newpages.add(url)
                        elif(url[0:1]=='/'):
                            url = 'http://en.people.cn' + url
                            if(not self.isindexed(url)):
                                newpages.add(url)
                    # 以上提取页面上的所有本站内部链接

                    linkText=self.gettextonly(link)
                    self.addlinkref(page,url,linkText)

                self.dbcommit()
            
            pages=newpages
    
    # Create the database tables
    def createindextables(self):
        self.con.execute('create table urllist(url)')
        self.con.execute('create table wordlist(word)')
        self.con.execute('create table wordlocation(urlid,wordid,location)')
        self.con.execute('create table link(fromid integer,toid integer)')
        self.con.execute('create table linkwords(wordid,linkid)')
        self.con.execute('create index wordidx on wordlist(word)')
        self.con.execute('create index urlidx on urllist(url)')
        self.con.execute('create index wordurlidx on wordlocation(wordid)')
        self.con.execute('create index urltoidx on link(toid)')
        self.con.execute('create index urlfromidx on link(fromid)')
        self.dbcommit( )


#pagelist=['http://en.people.cn/n3/2017/1218/c90000-9305342.html']
#crawler=crawler('')
#crawler.crawl(pagelist)
crawler=crawler('test.db')
crawler.createindextables()
crawler.con.close()