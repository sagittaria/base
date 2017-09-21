# -*- coding: utf-8 -*-

from numpy import *

randArr=random.rand(4,4) #4x4随机数组

randMat=mat(random.rand(4,4)) #数组转换成矩阵

invRandMat=randMat.I #求逆

product=randMat*invRandMat #乘积

err=product-eye(4) #看乘积与单位矩阵的误差