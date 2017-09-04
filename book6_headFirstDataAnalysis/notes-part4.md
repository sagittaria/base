#### ch010-main: 回归（假设检验和预测差不多就组成了数据分析）

上次加薪谈判成功了，考虑做“专门替人争取加薪”的生意，业务为（根据客户的期望）告诉客户他们应该提多大幅度的加薪。这章会结合散点图（只要两种变量成对出现并描述了一个数据点就能用散点图）和回归来预测“客户应该提多少幅度的加薪”。
```
head(employees,n=30) --显示前30条数据
plot(employees$requested[employees$negotiated==TRUE],employees$received[employees$negotiated==TRUE])
--用【x轴：要求的加薪幅度】和【y轴：得到的加薪幅度】画散点图
```
平均值图：也是一种散点图，显示与X轴上每个区间相对应的Y轴数值（该区间内所有Y的平均值），回归线就是最准确贯穿平均值图中各个点的那条线。
回归线对于线性相关的数据很有用，相关性强弱用相关系数 r 表示，r 的范围是[-1,1]，0表示无相关性，1或-1表示完全相关。
```
> cor(employees$requested[employees$negotiated==TRUE],employees$received[employees$negotiated==TRUE])
[1] 0.6656481        --用R计算相关系数
```
回归线就是条直线，所以能用 y=a+bx 来描述，实际上可以直接用 R 求截距 a 和 斜率 b，
```
> myLm <- lm(received[negotiated==TRUE]~requested[negotiated==TRUE],data=employees)
> myLm$coefficients
                  (Intercept) requested[negotiated == TRUE]
                    2.3121277                     0.7250664
--先创建了一个LinearModel，叫做myLm，然后取它的系数coefficients，显示出截距2.3和斜率0.7，所以本章数据得到回归线就是 y=2.3+0.7x
```
在 R 里，斜率b的计算方式：```b = r * σ[y] / σ[x]  --相关系数乘以y的标准差除以x的标准差```，感觉应该知道下。 拿着上面那个回归线方程，就能给客户用了。其中有两个人，甲提了3%（y=4.4）结果加了5%，乙提了15%（y=12.8）结果没加，这就是下一章要说到的误差了。

#### ch011-main: 误差--控制误差，降低误差
ch10结尾说到，加薪计算器的预测结果与实际获得的加薪相比，可能极其精准，可能较为接近，也可能偏差巨大。

有人要求加薪25%，结果没加到还被解雇了——手上有的数据是0~22%，要算25%其实就是在用外插法，which is 非常说不好的，所以回归线方程只该用于0~22%范围内。

安抚了25%之后，再来看那些预测结果与实际相差不是非常大的情况。这种差别称为 机会误差（chance errors）或残差（residuals），对残差的分析是优秀的统计模型的核心，能正确解释残差便能更好地理解手头的数据和模型的用途。所以不要就一句```由于存在机会误差，你个人的实际可能不同于预测结果```来打发客户，应该```尽量为自己的模型和分析增加解释与预测功能，即周到地对待误差，而非单纯地让误差减小```

均方根误差：定量描述实际和预测值之间可能有多大误差，或者说数据点相对于回归线的平均偏移量，类似标准差之于平均值，区别：标准差描述的是一个变量（直方图的纵轴其实不重要），均方根误差则是两个变量之间的关系（两个成对出现的量组成一个数据点）。

均方根误差公式：```σ[y] * sqrt(1-r^2), r 相关系数```，直接用R计算：
```
> employees <- read.csv("D:/hfda_ch10_employees.csv", header=TRUE)
> myLm <- lm(received[negotiated==TRUE]~requested[negotiated==TRUE], data=employees)
> summary(myLm)$sigma
[1] 2.297743
```
然后就能说，实际能获得的加薪应该与预测值相差不超过±2.5%，画到图P340上
但客户普遍认为2.5%太大了，先试试分区间来重算均方根误差，画到P344图上可以看到以10%为界，左右两侧的均方根误差（分布风格）明显不同。
所以用10%左右两侧的数据分别做回归，得到两条回归线P348，并分别标记上R.M.S（root mean squared）
    
上图展示的信息：可以为胆小谈判者和激进谈判者分别选用合适的回归线方程。下面是用R做出这两条回归线，并获取R.M.S：
```
myLmBig <- lm(received[negotiated==TRUE & requested > 10]~requested[negotiated==TRUE & requested > 10],data=employees)
myLmSmall <- lm(received[negotiated==TRUE & requested <= 10]~requested[negotiated==TRUE & requested <= 10],data=employees)
summary(myLmSmall)$coefficients
summary(myLmSmall)$sigma
summary(myLmBig)$coefficients
summary(myLmBig)$sigma
```
现在大家可以自己决定，要冒险狮子大开口，还是宁可降低要求图个安稳。求安稳的人心想事成，不惧风险的人也能理解他们为什么会有这种结果。
