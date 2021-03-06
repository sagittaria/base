#### ch01-main: 信息可视化

MM公司需要一些图表，下届游戏博览会上的主题演讲中，要求巧妙直接地展示数据。开始介绍：

饼图：用于各组之间的比较（相对多少很容易看出来），当然如果各组的量值很接近就看不出明显区别，图的意义也就丧失了

柱状图：相对饼图更精确；垂直的较多用，如果分组的组名太长可以考虑水平柱状图P11，还有柱状图上代表的百分数和
频数 __都必须留意__ ，可用分段或堆积型柱状图展示多批数据P14。

数据的分类：1-Categorical or qualitative data 翻译成了定性数据，例如对狗按品种分类，各个品种（松狮、拉布拉
多、阿拉斯加）就是categorical data；2-Numerical or quantitative data 定量数据，就是尺量出来的长度、天平称出
来的质量，这类数据。

直方图：与柱状图相比，区别在于每个长方形【__柱之间没有间隔__】，而且每个长方形的【 __面积__ 】与频数成正比（直
方图每个柱的宽度并不是非相等不可）。表P19为各个分数段的票数，图P22是对应的直方图（这个图上的区间宽度是相等的）。

对于区间宽度不等的数据组P24，也能画直方图P25。但这个图（which is not 直方图）画出来，看着很失衡——真正问题在于，
这个图上每个长方形柱的【__面积__】并没有反应频数，即 __不应该以频数为纵轴__ 。考虑到“面积(而非高度)-->反应-->频数”这
个原则，直方图上的高度【称为频数密度，可衡量频数密集度】应该用：频数÷区间宽度。

算出高度（频数密度）之后，可以画出正确的直方图P28。总结：直方图上每个长方形的高度不是频数，而是频数密度。

累积频数图P35：直方图也有力所不及的时候，比如要用它来的统计在线时长少于4小时的玩家人数有多少，就很麻烦了。
根据P34上表的统计数据，可以考虑画“累积频数图”。

折线图P39：常用于体现趋势，与柱状图相比更容易添加多组数据（而不弄乱原图，也不易显得拥挤）

#### ch02-main: 集中趋势的度量-平均数：均值、中位数、众数

健身房意识到客户跟同龄人在同一个班上练习时，表现更开心，而开心的人更常做回头客。所以，你知道该做什么了。

平均数average有好几种，均值mean是所有数加起来除以数字个数，常用符号为μ，```μ=(∑x)/n```；计算均值的时候，是考虑了频数的，所以P52均值mean的公式也能写成```μ=(∑fx)/∑f ```。

常见的例子：某工厂由于管理层领导的工资远远的整个厂的薪资均值，导致均值无法“典型地”代表该厂的薪资水平。——异常值的存在可能导致均值不具代表性。类似的，由于功夫班有超高龄的宗师，该班的年龄均值被拉高了P58。
  
此时可以取另一种平均值，即中位数median来表示典型值。对于功夫班，中位数是20，更“典型地”代表了班里人的年龄。

但是有了中位数和平均数也不能保证百分百“合适”。因为健身房有个幼儿游泳班，把孩子和家长都当成是班上成员记了年龄P68，
```
年龄     1     2     3     31     32     33
人数     3     4     2     2     4     3
```
P68图上能看到，明显是两组(批)数据，但中位数和平均数确实都是17。如果一个十几岁的teenager来报游泳班，会发现一个同龄人也没有
对于这情境下的年龄数据，可以用第三种平均数：众数mode（频数最高的一个或几个数据），来作为典型值。比如游泳班年龄的众数就是2、32（就它俩频数最高，都是4）。众数数目较少/数据为categorical类型时，众数有用P76。
