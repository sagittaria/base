#### ch07-main: 主观概率
概念：a numerical probability to your degree of belief in something，用于when you are predicting single
 events that lack hard data to describe what happened previously under identical conditions.

背水投资公司的分析师们的分歧：1.俄罗斯下一季会不会补贴石油业，2.俄是否会收购欧航，3.越南今年是否会减税，
4.越南今年是否会鼓励外国投资，5.印尼旅游业今年是否会翻身，6.印尼政府是否会投资生态旅游

他们表达观点用的词： probably, highly unlikely, more likely, might, probably aren’t, unlikely, may,
definitely, and good chance.

我们要让那些判断显得更精确，让他们说清楚认为俄支持石油业的几率是90%或80%还是70% ，列到表格P203里汇总
但是只有这些数字并不直观，不便于分析判断，所以画成散点图P206（纵坐标其实无所谓）
用标准差来描述分歧有多大，标准差越小表明分歧也越小。EXCLE有标准差函数：=STDEV(A1:A10)

故事继续。早上新闻说，俄罗斯宣布出售所有油田，这让分析师们很无语，给他们机会重新修灯主观概率

接下来再次请出 visualization 和 Bayesian Statistic (__用分析师们的第一个主观概率做为“基础概率”__)，
—— A. 先想办法获得更准确的数据，B. 再考虑图形化（重新画P206图）

P(H)假设成立的概率，P(H|E)某证据出现的话，假设成立的概率，P(E|H)假设成立的话，某证据出现的概率
```
P(H|E) = P(H)*P(E|H)/[P(H)xP(E|H)+P(~H)xP(E|~H) ]
```
为了真正计算，__还需要分析师提供他们认为的 P(E|H) 和 P(E|~H)__，添加到P203电子表格就能用公式求P(H|E)了

针对每个观点处理，计算出重画P206图所需的数据，画好发现statement1的图形变化不大，而且后来的事证明分析师们
关于【俄罗斯尽管可能卖油田（也许只是虚张声势），但基本上还是会支持石油业】的判断是对的（当人们意识到确实
是虚张声势之后，股市立即反弹）。



















