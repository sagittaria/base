### Define/确定 -> Disassemble/分解 -> Evaluate/评估 -> Decide/决策
#### ch01-main: P35 路线图
一开始试图提高少女消费者市场的销量，因为当时相信她们是唯一的客户群

当发现少女消费者市场已经饱和，继续挖掘，发现上了年纪的男性也喜欢用于剃须后的护理

这个消费群体不宣扬对产品的热衷，1.建议增加对这个群体的广告宣传，

2.用更易被男性接受的特色销售同样的产品

————问题：以上写的并没有体现“确定、分解、评估、决策”这条链


#### ch02-main: 
咖啡销量下降，要想出回复销量的方法。

拿市场部的每月一次的客户调查数据，通过比较能看出人们对咖啡价值的评分在快速下降

但实际上通过已有数据没法知道是否价值的下降导致了销量下滑

富人区分店的店长：我的店里根本没有“价值问题”

而且，客户的财富<实际上难以获得>和咖啡店的店址都可能影响分析结果

为此，按“富人区”、“平民1区”、“平民2区”分三组来看客户调查数据，平民1区和2区之间除了地理位置其他差别不大

分组后的数据可以看到富人区的确没有价值问题，平民两个区有，而且挺严重。

CFO说试试降价，marketing想雇人游说“咖啡有价值”，CEO听了CFO的，说全部分店都采取降价措施

实验一个月，销量确实飞涨了，但其实无法计算“比不降价多赚了多少”

为了有控制组（对照组），重新做实验，平民1区降价（实验组），富人区和平民2区不变（对照组），发现实验组销量胜出

但实验组里有混杂因素（同时包含了富人和平民），结果说服力较弱，所以重新--随机--分组
```
随机分组策略：<P66具体说明和P67图>
1.将大的地理区域分成小的地理区域，随机将这些微区域分进控制组和实验组。
2.分割时，要使每个区域足够大，使人们不至于为了喝杯【便宜】咖啡而穿越城市；
3.且要使每个区域足够小，使每个区内的分店没有太大差异。
```
结果为“价值游说组”胜出，——通过随机控制实验找出了与经验吻合的销售策略


#### ch03-main: 优化
bathing_friends_unlimited.xls：使用excel的solver插件做个线性优化，给定原料量、工时和两种产品的盈利能力，设计利润最大的生产方案

分析结果：为了获得最大利润，要生产400鸭、80鱼，但销售结果为：80鱼全都卖光了，400鸭只卖出了20，利润仅为420，远比预计的2320少

因为模型中漏了一个重要的假设条件：没有任何因素表明人们会真正购买此产品，所以要增加一个体现人们会买什么的假设

然后找来了整个橡皮玩具行业的鸭和鱼的历史销售数据，能从中看到两种产品的销量基本上是此消彼长的——不知道为什么，但可以肯定这两者是负相关关系。

此外，表中数据还显示每年1月会有个销量的骤降。

现在要做的是：增加一个新的约束，用于估计某个月的鸭和鱼的需求量。具体认为，鸭卖不过150，鱼卖不过50，建议就按这两个量生产。

所以在.xls文件里给产量加上这两个约束条件，再跑一次，预计利润为950，不如之前高但更合理。

————问题：没有特别明确定量的方法来得出上面的“150、50”，只是靠眼观历史销售数据趋势来估。这种预测可能不准，随时需要调整。


#### 感觉没怎么体现“学到”了什么