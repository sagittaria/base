#### ch11-main: 总体和样本的估计（这里population翻译过来是总体）

样本有了，现在要根据样本估算总体的（糖球口味停留时间）均值。对于无偏样本，样本均值应该是接近总体均值的，如图P443

总体参数的点估计量：比如用【样本均值】估计【总体均值】，那么样本均值就是【总体均值的点估计量】，点估计量通常在总体参数的符号上加个乘方标记以示区分，样本均值的一般用x再在上面加一横（读作“x拔”）。以上三个中括号里的概念和用在何处，应能明确区分P447。

再看总体方差：它的点估计量公式与样本方差类似（P449），但分母是n-1，因为抽样总归是 __取总体的一部分，所能涵盖的“差异性”自然小了__ （图形上的表现见P443，总体方差一般比样本方差大一点），所以除个更小点的分母使结果更接近总体方差的真实值。

【以上是样本->总体，现在过渡到，总体->样本】电影院获得授权卖曼迪的糖球，有个顾客说，我就爱吃红色糖球，其他都不爱吃，盒子里有几颗红色的？我要嚼40颗红的才能看完整场电影，如果一盒子里红的少于40颗，那我宁愿买其他零食。

[已知]每盒有100粒，糖球总体有25%是红色的，[求]一盒中至少有40颗红的概率是多少？[提示]1.ch7和ch9有用泊松和正态分布近似替代原概率分布的条件。2.__用连续的正态分布近似代替离散的二项分布时，不要忘了“连续性修正”__

准备：1.样本大小n=100，2.总体中红色糖球的比例P=0.25（样本中的比例应该相同，也为0.25），3.样本中红色糖球颗数X符合二项分布，```X~B(100,0.25)```. 4.样本中红色糖球的比例取决于红色糖球的数目，所以这个比例本身是个随机变量，```Ps=X/n```

复习：一般的```E(aX)=aE(X), VAR(aX)=a^2*VAR(X)```。对于符合二项分布的```X~B(n,p), E(X)=np, VAR(X)=npq```。

过程：1.求Ps的期望方差，```E(Ps)=E(X)/n=np/n=p=0.25, VAR(Ps)=VAR(X)/(n^2)=npq/(n^2)=pq/n=0.001875，标准差σ=sqrt(VAR(X))```，2.确认np>5且np>5可以用正态分布来近似（实际书上说的理由是n>30了，算n很大），于是```Ps~N(p, pq/n)```，3.【__连续性修正__】由于X的连续性修正是```±1/2```，所以Ps的连续性修是```±1/(2n)```，离散的```Ps(X/n≥0.4)```应该用连续的```Ps(X/n≥(0.4-1/(2*100)))=Ps(X/n≥0.395)```来算，4.按照套路[标准化、查表]得出```Ps(X/n≥0.395)=0.0004```.

结论：100粒一盒的包装中，红色糖球超过40粒的概率是0.0004.

【刚才求了“__样本比例__”的概率，现在要求“__样本均值__”的概率】除了大盒装，曼迪公司也出小袋装，每个小袋里的糖球数量均值10，方差1。有个忠实客户买了30袋，结果发现每袋的平均数目是8.5，于是投诉了...CEO想知道这种事发生的概率有多少，换句话说，__样本均值__小于等于8.5的概率是多少？

过程：1.样本数量n=30，2.总体的均值μ和方差σ^2已知，3.随机选的每袋糖球都是X的一个__独立观察结果__，每袋糖球Xi都符合相同的分布（μ, σ^2），4.取n包糖球，```X_bar=(X1+X2+...+Xn)/n, E(X_bar)=E((X1+X2+...+Xn)/n)=E(X1/n+X2/n+...Xn/n)=1/n*(E(X1)+E(X2)+...+E(Xn))=1/n*(nμ)=μ```书P474，5.方差VAR(X_bar)不同于总体的σ^2，__因为X代表的是一袋中的糖球数量，X_bar代表一个样本的糖球数量的均值，E(X_bar)表示所有样本均值的均值(好好理解这句话)__，VAR(X_bar)=σ^2/n（推导过程在P477），6.根据以上，有结论```如果X~N(μ, σ^2)，那么(X_bar)~N(μ, σ^2/n)```，7明确了X_bar的均值、方差，并确认X_bar近似正态分布

【插播中心极限定理】非正态整体X中取出一个样本，如果样本数量很大（如超过30），那么X_bar的分布近似为正态分布。

【插播中心极限定理】应用：二项分布或者泊松分布的整体中抽的样本较大，就能根据中心极限定理认为X_bar按正态分布，```X~B(n,p) -> X_bar~N(np,pq) ，X~Po(λ) -> X~N(λ,λ/n)```

【插播中心极限定理】说明：应用中心极限定理不需要进行连续性修正（__应用定理所求的概率与样本的均值有关，而与样本中的数值无关__）。

最后按照[标准化、查表]两步走，一查发现概率太小都没出现在表上，可以认为几乎不会出现。