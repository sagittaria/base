# -*- coding: utf-8 -*-

'''
基本原理：计算每两个群组间的距离，将距离最近的两个合并为一个新的群组，重复执行直到只剩一个群组
'''

from math import sqrt
from PIL import Image,ImageDraw
import random

def readfile(filename):
    lines=[line for line in open(filename)]
    colnames=lines[0].strip().split('\t')[1:]
    rownames=[]
    data=[]
    for line in lines[1:]:
        p=line.strip().split('\t')
        rownames.append(p[0])
        data.append([float(x) for x in p[1:]])
    return rownames,colnames,data

#blognames,words,data=readfile("blogdata.txt") #以上预处理：读入数据


def pearson(v1,v2):    
    sum1=sum(v1)
    sum2=sum(v2)    
    sum1Sq=sum([pow(v,2) for v in v1])
    sum2Sq=sum([pow(v,2) for v in v2])
    pSum=sum([v1[i]*v2[i] for i in range(len(v1))])
    num=pSum-(sum1*sum2/len(v1))
    den=sqrt((sum1Sq-pow(sum1,2)/len(v1))*(sum2Sq-pow(sum2,2)/len(v1)))
    if den==0: return 0
    return 1.0-num/den
    '''#因为num/den算出来的皮尔逊相关度，两组数据完全匹配时值为1，
    完全无关时值为0，所以用1减去相关度，则结果表现为越相似的东西，“距离”越近'''

class bicluster: #树的分枝节点或者叶节点（原数据集中的每一行对应叶节点）
    def __init__(self,vec,left=None,right=None,distance=0.0,id=None):
        self.left=left
        self.right=right
        self.vec=vec #单词向量，就是从文件中读到的行
        self.id=id
        self.distance=distance


def hcluster(rows,distance=pearson):
    distances={}
    '''这个字典键是两个索引值组成的元组，值是距离，
    如(0,1):0.3表示clust[0]和clust[1]之间的距离是0.3'''
    currentclustid=-1
    '''新合并出来的clust的id都用负数，第一个新聚类是-1，第二个是-2，以此类推'''
    clust=[bicluster(rows[i],id=i) for i in range(len(rows))]
    '''最初始时，每行数据(每个blog)自成一个clust'''
    while len(clust)>1:
        lowestpair=(0,1)#初始化一个距离最近的pair，即认为第0个和第1个博客最近
        closest=distance(clust[0].vec,clust[1].vec)
        
        for i in range(len(clust)):#遍历每个clust
            for j in range(i+1,len(clust)):#，和其他所有clust
                '''如果distances字典中还没存这对clust之间的距离
                也即字典中还没(i,j)这个键时：'''
                if (clust[i].id,clust[j].id) not in distances:
                    '''写成.id而不直接用i,j是由于以后会有新的clust，其id不在用下句
                    clust=[bicluster(rows[i],id=i) for i in range(len(rows))]
                    初始化时的id中'''
                    distances[(clust[i].id,clust[j].id)]=distance(clust[i].vec,clust[j].vec)
                    
                d=distances[(clust[i].id,clust[j].id)]
                
                if d<closest:
                    closest=d
                    lowestpair=(i,j)
                '''因为后面会把新的聚类append上来，所以i,j始终是clust列表的合法索引'''

        #求平均作为新聚类的单词向量
        mergevec=[(clust[lowestpair[0]].vec[i]+clust[lowestpair[1]].vec[i])/2.0
                    for i in range(len(clust[0].vec))]
    
        #用距离最近的那两个节点，创建新的聚类
        newcluster=bicluster(mergevec,left=clust[lowestpair[0]],right=clust[lowestpair[1]],distance=closest,id=currentclustid)
    
        # cluster ids that weren't in the original set are negative
        currentclustid-=1 #新合成的聚类的id用负数，依次递减，-2，-3，...
        del clust[lowestpair[1]]
        del clust[lowestpair[0]]#删掉原来最近的两个
        clust.append(newcluster)#把新合成的聚类append上去
    return clust[0] #最终返回了树的根节点

#clust=hcluster(data)

def getheight(clust):
    #如果是叶节点，高度为1；否则，高度是所有分支的高度之和
    if clust.left==None and clust.right==None: return 1
    return getheight(clust.left)+getheight(clust.right)
'''树的根节点在左，叶节点在右。height指竖直高度，depth是水平宽度'''
def getdepth(clust):
    #如果是叶节点，深度为0；
    if clust.left==None and clust.right==None: return 0
    #否则返回（其他分支中，深度最大的分支的深度值，加上本节点自身的深度）
    return max(getdepth(clust.left),getdepth(clust.right))+clust.distance


def drawdendrogram(clust,labels,jpeg='clusters.jpg'):
    h=getheight(clust)*20
    w=1200 #图幅：宽1200，高为竖直方向上的层数乘以20
    depth=getdepth(clust)
    scaling=float(w-150)/depth
    '''（尚不太明晰）因为图幅宽度已经定死，需对短横按比例缩放。上式在做什么：
    留出150用于放文字，剩下的宽度，如果要放depth层，计算每层短横的平均长度'''
    
    img=Image.new('RGB',(w,h),(255,255,255))#固定用法吧
    draw=ImageDraw.Draw(img)
    draw.line((0,h/2,10,h/2),fill=(255,0,0))#画从根节点出来的短横
    
    drawnode(draw,clust,10,(h/2),scaling,labels)#调递归函数
    img.save(jpeg,'JPEG')


def drawnode(draw,clust,x,y,scaling,labels):
    if clust.id<0:#如果不是叶节点
        h1=getheight(clust.left)*20
        h2=getheight(clust.right)*20 #左子树和右子树的高度
        top=y-(h1+h2)/2
        bottom=y+(h1+h2)/2#左子树和右子树的短横起点的纵坐标
        #短横长度：由“这个”非叶节点存的distance乘缩放因子所得
        ll=clust.distance*scaling 
        # 竖线
        draw.line((x,top+h1/2,x,bottom-h2/2),fill=(255,0,0))
        # 左子树的短横
        draw.line((x,top+h1/2,x+ll,top+h1/2),fill=(255,0,0))
        # 右子树的短横
        draw.line((x,bottom-h2/2,x+ll,bottom-h2/2),fill=(255,0,0))
        # 递归画左子树，和，右子树
        drawnode(draw,clust.left,x+ll,top+h1/2,scaling,labels)
        drawnode(draw,clust.right,x+ll,bottom-h2/2,scaling,labels)
    else:
        #如果是叶节点，在给定坐标的右5、上6处放博客名
        draw.text((x+5,y-6),labels[clust.id],(0,0,0))


#drawdendrogram(clust,blognames,jpeg='blogclust.jpg')

'''有时候在单行内的列聚类也是有意义的，如通过分析消费行为，用来发现哪些商品可以捆绑销售
在本章的博客例子中，列举类可以发现哪些单词会经常结合在一起使用'''
def rotatematrix(data): 
    newdata=[]
    for i in range(len(data[0])):
        newrow=[data[j][i] for j in range(len(data))]
        newdata.append(newrow)
    return newdata
'''对原来的data转置一下，就能用上面写好的方法执行聚类'''
#rdata=clusters.rotatematrix(data)
#wordclust=clusters.hcluster(rdata)
#clusters.drawdendrogram(wordclust,labels=words,jpeg='wordclust.jpg')
'''也可能会找出一些反映使用模式的聚类，us/say/very/think这些词的出现说明博客风格偏主观'''


'''k均值聚类'''
def kcluster(rows,distance=pearson,k=4):
    # 确定每列的最小和最大值
    ranges=[(min([row[i] for row in rows]),max([row[i] for row in rows]))
            for i in range(len(rows[0]))]
                
    # 随机生成k个中心点（每个点有 len(rows[0]) 列）,j只是个counter
    clusters=[[random.random()*(ranges[i][1]-ranges[i][0])+ranges[i][0]
        for i in range(len(rows[0]))] for j in range(k)]
        
    lastmatches=None
    for t in range(100):
        print('Iteration %d' % t)
        bestmatches=[[] for i in range(k)] #先用k个空列表填到bestmatches
        
        for j in range(len(rows)):#遍历每一行，确定它最接近于哪个中心点
            row=rows[j]
            bestmatch=0 #初始化“当前最近的中心”为第0个随机点
            for i in range(k):#看这行（这个“点”）和第几个中心点最接近
                d=distance(clusters[i],row)
                if d<distance(clusters[bestmatch],row):
                    bestmatch=i #如果距离更短，更新“最近点”
            bestmatches[bestmatch].append(j)#把第j行追加到代表第bestmatch个中心的列表里
    
        # 如果这次迭代结果和上次相同，说明聚类完成了
        if bestmatches==lastmatches: break
        lastmatches=bestmatches #否则更新“上一次”的聚类结果
        
        # 求均值更新聚类中心点
        for i in range(k):
            avgs=[0.0]*len(rows[0]) #将avgs初始化为包含“列数”个0的列表
            if len(bestmatches[i])>0: #如果第i个聚类中有内容
                for rowid in bestmatches[i]:#遍历这个聚类中的每行（每个博客）
                    for m in range(len(rows[rowid])):
                        avgs[m]+=rows[rowid][m]
                    '''按行全加起来，除以行数'''                        
                for j in range(len(avgs)):
                    avgs[j]/=len(bestmatches[i])
                    '''求得平均值，更新替换原来的第i个中心'''                
                clusters[i]=avgs
                
    return bestmatches

#clust=kcluster(data)
'''“初始化”过程中有随机因素，所以结果不很稳定'''

'''针对偏好的聚类'''
def tanimoto(v1,v2):#数据只有0,1，用Tanimoto coefficient(谷本系数)比皮尔逊系数更合适
    c1,c2,shr=0,0,0
    for i in range(len(v1)):
        if v1[i]!=0: c1+=1 # in v1
        if v2[i]!=0: c2+=1 # in v2
        if v1[i]!=0 and v2[i]!=0: shr+=1 # in both
    return 1.0-(float(shr)/(c1+c2-shr))
    
#所得结果图与书上不同的
wants,people,data=readfile('zebo.txt')
clust=hcluster(data,distance=tanimoto)
drawdendrogram(clust,wants)