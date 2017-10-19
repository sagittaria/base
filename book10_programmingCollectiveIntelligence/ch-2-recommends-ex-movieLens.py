# -*- coding: utf-8 -*-

from math import sqrt

path='C:/D/github/base/book10_programmingCollectiveIntelligence/'

movies={}

for line in open(path+'u.item_utf8',encoding='utf-8'):
    (id,title)=line.split('|')[0:2]
    movies[id]=title


prefs={}

for line in open(path+'u.data',encoding='utf-8'):
    (user,movieid,rating,ts)=line.split('\t')
    prefs.setdefault(user,{})
    '''
    含义是如果user是字典的键，返回该键的值；否则插入user为键，值为{}
    print(movies.setdefault('1',{})) 会打印出 Toy Story (1995)
    而对movies['1']的值并无影响（我原来的理解是错的）
    '''
    prefs[user][movies[movieid]]=float(rating)


#欧氏距离
def sim_distance(prefs,user,other):
    si={} #搜集shared_items
    for item in prefs[user]:
        if item in prefs[other]:
            si[item]=1
    
    if len(si)==0: return 0 #如果没有shared_items, 直接返回0
    
    square_sum=sum([pow(prefs[user][item]-prefs[other][item],2) for item in si])
    return 1/(1+sqrt(square_sum))


#皮尔逊相关系数
def sim_pearson(prefs,p1,p2):        
        si={}# 找大家都评过的电影
        for item in prefs[p1]:
                if item in prefs[p2]: si[item]=1

        n=len(si) # Find the number of elements        
        if n==0: return 0 # if nothing in common, return 0

        sum1=sum([prefs[p1][it] for it in si])
        sum2=sum([prefs[p2][it] for it in si])#求评分之和

        sum1Sq=sum([pow(prefs[p1][it],2) for it in si])
        sum2Sq=sum([pow(prefs[p2][it],2) for it in si])#平方和

        pSum=sum([prefs[p1][it]*prefs[p2][it] for it in si])#乘积和

        num=pSum-(sum1*sum2/n)
        den=sqrt((sum1Sq-pow(sum1,2)/n)*(sum2Sq-pow(sum2,2)/n))
        if den==0: return 0        
        r=num/den #皮尔逊相关度的算式
        return r


# 最佳匹配
def topMatches(prefs,user,n=5,similarity=sim_pearson):
    scores=[(similarity(prefs,user,other),other) 
                for other in prefs if other!=user]
    scores.sort()
    scores.reverse()
    return scores[0:n]


#基于用户的协作型过滤
def userBasedRecommendation(prefs,user,similarity=sim_pearson):
    total_sum={}
    sim_sum={}
    others=[other for other in prefs if other!=user]
    for person in others:
        sim=similarity(prefs,user,person)
        if sim<=0: continue 
        '''
        忽略负相关的人
        '''
        for item in prefs[person]: #其他人看过的
            if item not in prefs[user]: #但user没看过的
                total_sum.setdefault(item,0)
                '''
                必须要有setdefault，先定义a={}，直接赋值a['b']=4是可以的
                但如果直接a['b']=a['b']+5就不行了（KeyError），a['b']尚不存在
                '''
                total_sum[item]+=sim*prefs[person][item]
                sim_sum.setdefault(item,0)
                sim_sum[item]+=sim
    
    rankings=[(total_sum[item]/sim_sum[item],item) for item in total_sum]
    rankings.sort()
    rankings.reverse()
    return rankings


#人和物对调
def flip(prefs):
    items={}
    for user in prefs:
        for item in prefs[user]:
            items.setdefault(item,{})
            items[item][user]=prefs[user][item]
    return items

#计算物品相似度
def calculateSimilarItems(prefs,n=10):
    item_sim={}
    itemprefs=flip(prefs)
    c=0
    for item in itemprefs:
        item_sim[item]=topMatches(itemprefs, item, n=n, similarity=sim_distance)
        c+=1
        if c%100==0: print("%d / %d" % (c,len(itemprefs)))
    return item_sim
    
item_sim=calculateSimilarItems(prefs) #书上还传入了n=50

#基于物品的协作型过滤
def itemBasedRecommendation(prefs,itemMatch,user):
    userRatings=prefs[user]
    scores={}
    total_sim={}
    
    for (item1,rating) in userRatings.items():
        for (similarity, item2) in itemMatch[item1]:
            if item2 in userRatings: continue
            scores.setdefault(item2,0)
            scores[item2]+=rating*similarity
            total_sim.setdefault(item2,0)
            total_sim[item2]+=similarity
        
    rankings=[(scores[item]/total_sim[item],item) for item in scores]
    rankings.sort()
    rankings.reverse()
    
    return rankings
    
'''
跑一边上面的所有预处理和函数定义之后，在ipython里执行
userBasedRecommendation(prefs,'87')[0:30]
应会比
itemBasedRecommendation(prefs,item_sim,'87')[0:30]
慢一些
'''