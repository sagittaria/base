#### ch15-main: 相关和回归

利用散点图识别模式：日照时间和露天音乐会观众人数的关系，根据P608表，
画出P609图，
数据点大致呈直线分布——线性（linear）相关（correlation）。

线性相关有正有负P613；相关性__不等于__因果性（花粉-防晒霜）；__相关性描述的是线性关系__，当然并非所有关系都是线性的。

为了根据日照时间预测音乐会人数，在散点图上画最佳拟合线（a line of best fit）。

所谓“最佳”是指，能最好地接近所有数据点，即-使得-误差平方和(sum of sqared errors)```SSE=∑(yi_r-yi_e)^2```最小-的那条线。误差是真实值yi_r和（用拟合线）得出的估计值yi_e之间的差. 现在要做的是，找出使得SSE最小的a和b，代入y=a+bx就得到最佳拟合线。

证明就免了，直接给公式：```b=∑(x-x_bar)(y-y_bar)/∑(x-x_bar)^2```，算出b之后，用最佳拟合线必经过的(x_bar,y_bar)这个点代入算出```a=y_bar-b*x_bar```。以上就是“最小二乘回归法”求最佳拟合线的过程，所求得的回归线只适用于数据点范围内的预测P626

对于音乐会，用最小二乘回归法得到的表达式是```y=15.80+5.32x```. 虽然叫做最佳拟合线，但目前尚未知其准确性如何，所以要用一个__相关系数r__来描述这条线的“拟合程度”。
```
相关系数r=b*s_x/s_y，其中s_x是x的标准差, s_y是y的标准差
```
r=-1时完全负相关，r=0不相关，r=1完全正相关。r通常介于-1~1之间。
音乐会问题中，套公式计算出r=0.91，接近1，说明听众和预计日照时长之间有很强的相关性，可以期望最佳拟合线给出准确率良好的估计。

最后有个阿梅森上尉的超能力的例子，用excel做了截图如下（这种回归分析只要先画个普通的散点图，再在图上右击数据点选“添加趋势线”，按界面操作即可）
