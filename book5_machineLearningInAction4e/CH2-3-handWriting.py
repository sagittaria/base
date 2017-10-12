# -*- coding: utf-8 -*-
from numpy import *
from kNN import classify0
from os import listdir #列出给定目录下的文件名

def img2vector(filename): #“图”转成形状为1x1024的横向量
    returnVect = zeros((1,1024))
    fr = open(filename)
    for i in range(32):
        lineStr = fr.readline()
        for j in range(32):
            returnVect[0,32*i+j] = int(lineStr[j])
    return returnVect

#选个数字，打印出来验视
#testVector = img2vector('testDigits/0_13.txt')
#for i in range(1024):
#    print(int(testVector[0][i]),end='')
#    if (i+1)%32==0:
#        print()


def handwritingClassTest():
    hwLabels = []
    trainingFileList = listdir('trainingDigits')
    m = len(trainingFileList)
    trainingMat = zeros((m,1024))
    for i in range(m):
        fileNameStr = trainingFileList[i]
        fileStr = fileNameStr.split('.')[0]
        classNumStr = int(fileStr.split('_')[0])
        hwLabels.append(classNumStr)
        trainingMat[i,:] = img2vector('trainingDigits/%s' % fileNameStr)
    testFileList = listdir('testDigits')
    errorCount = 0.0
    mTest = len(testFileList)
    for i in range(mTest):
        fileNameStr = testFileList[i]
        fileStr = fileNameStr.split('.')[0]
        classNumStr = int(fileStr.split('_')[0])
        vectorUnderTest = img2vector('testDigits/%s' % fileNameStr)
        classifierResult = classify0(vectorUnderTest, trainingMat, hwLabels, 3)
        if (classifierResult != classNumStr):
            errorCount += 1.0
            print("预: %d, 实: %d 文件: %s" % (classifierResult, classNumStr, fileNameStr))
    print("\nthe total number of errors is: %d" % errorCount)
    print("\nthe total error rate is: %f" % (errorCount/float(mTest)))

#handwritingClassTest()

'''
PS:实际玩起来效果并不好[笑哭]，而且运行很慢，另外因为样本(文件)数太多所以并没有git add
'''