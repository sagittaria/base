# -*- coding: utf-8 -*-

from numpy import *
import operator

def createDataSet():
    group = array([[1.0,1.1],[1.0,1.0],[0,0],[0,0.1]])
    labels = ['A','A','B','B']
    return group, labels

group,labels=createDataSet()

def classify0(inX, dataSet, labels, k):
    dataSetSize = dataSet.shape[0]
    diffMat = tile(inX, (dataSetSize,1))-dataSet
    sqDiffMat = diffMat**2
    sqDistances = sqDiffMat.sum(axis=1)
    distances = sqDistances**0.5
    sortedDistIndicies = distances.argsort()
    classCount={}
    for i in range(k):
        voteIlabel = labels[sortedDistIndicies[i]]
        classCount[voteIlabel] = classCount.get(voteIlabel,0) + 1
    
    sortedClassCount = sorted(classCount.items(), key=operator.itemgetter(1), 
                              reverse=True) #对于Python2，items改为iteritems
    return sortedClassCount[0][0]
    
res=[]
res.append(classify0([1. , 1.1],group,labels,3))
res.append(classify0([1. , 1. ],group,labels,3))
res.append(classify0([0. , 0. ],group,labels,3))
res.append(classify0([0. , 0.1],group,labels,3))

#print(res)

print("预测(1.2,1.3)的标签是",classify0([1.2, 1.3],group,labels,3))