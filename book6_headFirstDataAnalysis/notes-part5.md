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

#### ch013-main:
一家猎头公司成了我们的客户。他们搞到了一份求职人员名单，但内容是raw data，需要我们来帮忙清洗。用到的技能点：

1 数据标签页下的“分列”P394：根据指定的分隔符把一列拆分成多列

2 更多Excel函数：
FIND：查找位置；LEFT：左起截取；VALUE：字符串形式的数字转成数值；TRIM：去除空格； RIGHT：右起取值；CONCATENATE：拼接；LEN：单元格内容的长度；SUBSTITUTE：替换

3 在R的sub函数里用正则表达式：```NewLastName <- sub("\\(.*\\)","",hfhh$LastName) ```把左右括号及之间的内容全部去掉。然后用处理好的NewLastName替换原来LastName，
```
hfhh$LastName <- NULL
hfhh["LastName"] <- NewLastName
wirte.csv(hfhh, file="hfhh.csv")
```
会在当前目录生成hfhh.csv，可用getwd()查看当前工作目录

5 以PersonID排序```hfhhSorted <- hfhh[order(hfhh$PersonID), ]```，```head(hfhhSorted, n=50)```可以看到有重复项，Excel“数据”标签页下有“删除重复项”。

```
> head(hfhhSorted,n=10)
    PersonID FirstName   ZIP        Phone CallID            Time    LastName
63         1    Jaylen 10006 646-376-4466   4314 1/29/2008 11:19 Christensen
435        1    Jaylen 10006 646-376-4466   4686 7/30/2008 10:27 Christensen
540        1    Jaylen 10006 646-376-4466   4791 10/6/2008 16:08 Christensen
647        1    Jaylen 10006 646-376-4466   4898 12/6/2008 15:50 Christensen
712        1    Jaylen 10006 646-376-4466   4963 1/11/2009 15:20 Christensen
831        1    Jaylen 10006 646-376-4466   5082 3/17/2009 11:13 Christensen
974        1    Jaylen 10006 646-376-4466   5225 5/22/2009 14:58 Christensen
20         2  Reynaldo 11223 646-382-3431   4271  1/11/2008 8:06      Harris
347        2  Reynaldo 11223 646-382-3431   4598  6/17/2008 8:01      Harris
421        2  Reynaldo 11223 646-382-3431   4672 7/20/2008 14:31      Harris
```
我们猜测重复的多条记录基本上是因为倒闭的那家猎头公司给每个人在不同时间打了多个电话（从关系型数据库里查出这样的表结构很正常），

6 书上演示的是用R删除重复项：先存到另一个变量里（以防操作失误吧大概），去掉使出现重复记录的CallID和Time两列，再用unique函数去除重复条目。可预览结果确认下，最后写到文件里。
```
hfhhNamesOnly <- hfhhSorted
hfhhNamesOnly$CallID <- NULL
hfhhNamesOnly$Time <- NULL
hfhhNamesOnly <- unique(hfhhNamesOnly)
head(hfhhNamesOnly, n=50)
write.csv(hfhhNamesOnly, file=“hfhhNamesOnly.csv”)
```
交付hfhhNamesOnly.csv，客户很满意。一点额外的感想：如果看外文书的中文版，务必至少找到原书英文电子院，毕竟总有妨碍理解的翻译，无可避免的。
