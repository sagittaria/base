#### ch012-main:
某新闻杂志要找出文章书目和销量的关系，找出在每期刊物上登载文章的最优数量（要是登100篇比起登50篇销量并无提高，那就不登那么多；要是登50篇比登10篇销量高，那就登50篇，etc）。

两个excel文件，统计每期杂志的文章数，这个公式逼格相当高了。__countif函数__：统计文章数 

另一个高逼格的 __sumif函数__ ：统计销量 

给hfda_ch12_issues.csv的C、D两列加列名 Article count 和 Sales，然后读到R里，
```
> dispatch <- read.csv("D:/hfda_ch12_issues.csv",header=TRUE)
> head(dispatch)
  issueID  PubDate Article.count Sales
1       1 10/24/04             7  2227
2       2 2011/8/4             5   703
3       3 11/23/04             7  2252
4       4 2012/8/4             7  2180
5       5 12/23/04             8  2894
6       6 2001/7/5             7  2006
```
用```plot(Sales~jitter(Article.count),data=dispatch)```画散点图P373（下左图），下右图是不加jitter函数的结果。可见jitter就是给Article.count加点噪声，使图形更加可辨一些。
   
图上显而易见，文章数量应该定在10篇。接着，杂志社还想要了解哪些作者最受欢迎，保证每期都登他们的文章。用R画出lattice散点图展示每个作者的每篇文章的点击率和评论数P381。
```
> articleHitsComments <- read.csv("D:/hfda_ch12_articleHitsComments.csv",header=TRUE)
> head(articleHitsComments)
  articleID      authorName webHits commentCount
1         1   Destiny Adams    2019           14
2         2 Jon Radermacher    1421            6
3         3     Matt Janney    1174            8
4         4     Matt Janney    1613           26
5         5    Paul Semenec    1099           10
6         6   Destiny Adams    1903           26
> library(lattice)  --加载这个库
> xyplot(webHits~commentCount|authorName,data=articleHitsComments)  --竖线表示用authorName分格子
```
图P381上清晰显示了各个作者受欢迎程度，很赞。
