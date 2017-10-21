# -*- coding: utf-8 -*-

'''
基本原理：计算每两个群组间的距离，将距离最近的两个合并为一个新的群组，重复执行直到只剩一个群组
'''

from math import sqrt
from PIL import Image,ImageDraw

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

blognames,words,data=readfile("blogdata.txt") #以上预处理：读入数据


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

clust=hcluster(data)

def getheight(clust):
    # Is this an endpoint? Then the height is just 1
    if clust.left==None and clust.right==None: return 1
    # Otherwise the height is the same of the heights of each branch
    return getheight(clust.left)+getheight(clust.right)


def getdepth(clust):
    # The distance of an endpoint is 0.0
    if clust.left==None and clust.right==None: return 0
    # The distance of a branch is the greater of its two sides
    # plus its own distance
    return max(getdepth(clust.left),getdepth(clust.right))+clust.distance


def drawdendrogram(clust,labels,jpeg='clusters.jpg'):
    # height and width
    h=getheight(clust)*20
    w=1200
    depth=getdepth(clust)
    # width is fixed, so scale distances accordingly
    scaling=float(w-150)/depth
    # Create a new image with a white background
    img=Image.new('RGB',(w,h),(255,255,255))
    draw=ImageDraw.Draw(img)
    draw.line((0,h/2,10,h/2),fill=(255,0,0))
    # Draw the first node
    drawnode(draw,clust,10,(h/2),scaling,labels)
    img.save(jpeg,'JPEG')


def drawnode(draw,clust,x,y,scaling,labels):
    if clust.id<0:
        h1=getheight(clust.left)*20
        h2=getheight(clust.right)*20
        top=y-(h1+h2)/2
        bottom=y+(h1+h2)/2
        # Line length
        ll=clust.distance*scaling
        # Vertical line from this cluster to children
        draw.line((x,top+h1/2,x,bottom-h2/2),fill=(255,0,0))
        # Horizontal line to left item
        draw.line((x,top+h1/2,x+ll,top+h1/2),fill=(255,0,0))
        # Horizontal line to right item
        draw.line((x,bottom-h2/2,x+ll,bottom-h2/2),fill=(255,0,0))
        # Call the function to draw the left and right nodes
        drawnode(draw,clust.left,x+ll,top+h1/2,scaling,labels)
        drawnode(draw,clust.right,x+ll,bottom-h2/2,scaling,labels)
    else:
        # If this is an endpoint, draw the item label
        draw.text((x+5,y-7),labels[clust.id],(0,0,0))


drawdendrogram(clust,blognames,jpeg='blogclust.jpg')