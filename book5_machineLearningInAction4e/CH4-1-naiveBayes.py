# -*- coding: utf-8 -*-

from numpy import *

listOPosts=[['my', 'dog', 'has', 'flea', 'problems', 'help', 'please'],
            ['maybe', 'not', 'take', 'him', 'to', 'dog', 'park', 'stupid'],
            ['my', 'dalmation', 'is', 'so', 'cute', 'I', 'love', 'him'],
            ['stop', 'posting', 'stupid', 'worthless', 'garbage'],
            ['mr', 'licks', 'ate', 'my', 'steak', 'how', 'to', 'stop', 'him'],
            ['quit', 'buying', 'worthless', 'dog', 'food', 'stupid']]
             
listClasses = [0,1,0,1,0,1] #这是人工给上面六条留言标记的，1-含侮辱性文字, 0-正常言论(不含脏话)

def createVocabList(dataSet):
    vocabSet = set([])
    for document in dataSet: #每个词条，去掉重复的单词
        vocabSet = vocabSet | set(document) #加到vocabSet里(竖线表示求并集)
    return list(vocabSet)

def setOfWords2Vec(vocabList, inputSet):
    returnVec = [0]*len(vocabList)
    for word in inputSet: #如果input中的某个值
        if word in vocabList: #在词汇表里也存在（此处不在乎word是不是脏字，只为计每个单词的出现次数）
                returnVec[vocabList.index(word)] = 1 #把那个词所在的位置标1
        else: print("the word: %s is not in my Vocabulary!" % word)
    return returnVec
    #返回的是个列表，每个值代表对应单词出现的次数

myVocabList=createVocabList(listOPosts) #取到所有出现过的单词，放进一个set里

idx_of_stupid=myVocabList.index('stupid')
for i in range(6):
    wordVec=setOfWords2Vec(myVocabList,listOPosts[i]) #可验证在第1、3、5条出现了stupid
    print('stupid出现在第%d条listOPosts中？%d' % (i,wordVec[idx_of_stupid]))

def trainNB0(trainMatrix,trainCategory):
    numTrainDocs = len(trainMatrix) #留言条数
    #第0条里的单词数，其实每条都一样，因为setOfWords2Vec里指定了长度是按照词汇表来的
    numWords = len(trainMatrix[0])
    pAbusive = sum(trainCategory)/float(numTrainDocs) #出现脏留言的概率
    p0Num = ones(numWords)
    p1Num = ones(numWords)
    p0Denom = 2.0
    p1Denom = 2.0 #分母英文denominator
    for i in range(numTrainDocs):
        if trainCategory[i] == 1:
            p1Num += trainMatrix[i] #全脏留言中，每个单词的出现次数（列表形式）
            #作为zeros()函数返回的数组(向量)，是可以直接和列表加的
            #比如 a=array([1,1,1]),b=[0,1,2], a+=b之后a=array([1,2,3])
            p1Denom += sum(trainMatrix[i]) #所有脏留言中，单词个数合计
        else:
            p0Num += trainMatrix[i] #全正常留言中，每个单词的出现次数（列表形式）
            p0Denom += sum(trainMatrix[i]) #所有正常留言中，单词个数合计
    p1Vect = log(p1Num/p1Denom)
    p0Vect = log(p0Num/p0Denom)
    #上两式算的是脏留言和正常留言中，每个词出现的频率（列表形式）
    return p0Vect,p1Vect,pAbusive

trainMat=[] #制作训练样本
for postinDoc in listOPosts: #把每一条留言转换成词频向量之后添进trainMat
    trainMat.append(setOfWords2Vec(myVocabList, postinDoc))
    
p0V,p1V,pAb=trainNB0(trainMat,listClasses)

for i in range(len(myVocabList)):
    print(str(('%.3f' % p0V[i]))+"\t"+str(('%.3f' % p1V[i]))+"\t",end="")
    print(str(myVocabList[i]))
    #观察下，stupid出现概率最高，意为着它是最能表征“脏留言”类别的单词
    
def classifyNB(vec2Classify, p0Vec, p1Vec, pClass1):
    p1 = sum(vec2Classify * p1Vec) + log(pClass1)#这两行理解不了——————————————
    p0 = sum(vec2Classify * p0Vec) + log(1.0 - pClass1)#这两行理解不了————————
    if p1 > p0:
        return 1
    else:
        return 0

def testingNB(listOPosts,listClasses):
    myVocabList = createVocabList(listOPosts) #词汇表
    trainMat=[] #训练样本
    for postinDoc in listOPosts:
        trainMat.append(setOfWords2Vec(myVocabList, postinDoc))
    p0V,p1V,pAb = trainNB0(array(trainMat),array(listClasses))
    testEntry = ['love', 'my', 'dalmation']
    thisDoc = array(setOfWords2Vec(myVocabList, testEntry))#列表转数组
    print(testEntry,'classified as: ',classifyNB(thisDoc,p0V,p1V,pAb))
    testEntry = ['stupid', 'garbage']
    thisDoc = array(setOfWords2Vec(myVocabList, testEntry))
    print(testEntry,'classified as: ',classifyNB(thisDoc,p0V,p1V,pAb))

testingNB(listOPosts,listClasses)

def bagOfWords2VecMN(vocabList, inputSet):
    returnVec = [0]*len(vocabList)
    for word in inputSet:
        if word in vocabList:
            returnVec[vocabList.index(word)] += 1 #把词集模型的=1改成词袋模型的+=1
    return returnVec