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
