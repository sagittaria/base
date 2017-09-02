#### ch09-main: 直方图

你想要加薪，到不知道怎么做（给自己客观评价还是吹嘘吹嘘自己的才干？是否主动提？加多少？）于是从人力那边搞来了近三年的加薪记录 hfda_ch09_employees.csv。总共有3000条，考虑先分解一下，比如按“男”“女”“4~5%”，或者稍微复杂点按“要求加薪的女”、“2008年的男”分块，然后通过直方图（P263）来分析。

【注意】直方图histogram应该是连续的（P263上图），用Excel可能画出有缺口的图（P263下图，which is wrong）

-------------------------开始使用R-------------------------
自己起个tomcat服务器，做 webapps/hfda 目录，里面放 hfda.R 和 hfda_ch09_employees.csv，用文本编辑器打开 hfda.R，内容改为
employees <- read.csv("http://localhost:8080/hfda/hfda_ch09_employees.csv",header=TRUE)

在R的控制台执行
```
> source("http://localhost:8080/hfda/hfda.R")
> employees
        X received negotiated gender year
1       1     12.1       TRUE      M 2005
2       2      8.9       TRUE      F 2006
3       3      8.8       TRUE      M 2007
4       4      7.1       TRUE      F 2008
5       5     10.2       TRUE      M 2009
......(共3000条数据)
```

（in case 哪天真的从网络上读取数据，也就是说source后面用的是一般的Internet地址），等source执行完之后，输入 save.image() 回车，可以保存会话，默认存在 C:\Users\夏舒阳\Documents\ .RData，下次再载入工作空间，就能直接读取 employees 这个量了（但实际上可能它默认就会读 .RData，所以重新打开直接employees又是在的）

hist(employees$received,breaks=50) 生成直方图，还有
```
> sd(employees$received)
[1] 2.432138   --标准差
> summary(employees$received)
   Min. 1st Qu.  Median    Mean 3rd Qu.    Max.
 -1.800   4.600   5.500   6.028   6.700  25.900  --汇总统计信息（最大、最小、中位数、平均数等）
```
不是很懂P268“两个峰”是什么意思...接着是为了弄清为什么有两个峰，用数据的子集画直方图
```
hist(employees$received[employees$year == 2007], breaks = 50) 
hist(employees$received[employees$year == 2008], breaks = 50)
hist(employees$received[employees$gender == "F"], breaks = 50)
hist(employees$received[employees$gender == "M"], breaks = 50)
hist(employees$received[employees$negotiated == FALSE], breaks = 50)
hist(employees$received[employees$negotiated == TRUE], breaks = 50)
```
都很好懂，就不逐个解释了，绘制 negotiated == FALSE 和 negotiated == TRUE 两个子集的直方图，并和第一次画的完整数据的直方图放到一起比较，说是后两个图成功拆分出了第一个图的两个峰（？）。
图2：没有提加薪的人很多，获得过的加薪幅度小；图3：提了加薪的人较少，得到的加薪幅度更大。图2和3并到一起差不多是这么个意思：
所以结论很明显：获得大幅度加薪全靠主动提要求。进而分别汇总“提”和“不提”的两个子集的统计信息：
```
> summary(employees$received[employees$negotiated == FALSE])
   Min. 1st Qu.  Median    Mean 3rd Qu.    Max.
  1.700   4.300   5.000   4.994   5.700   8.100
> summary(employees$received[employees$negotiated == TRUE])
   Min. 1st Qu.  Median    Mean 3rd Qu.    Max.
 -1.800   6.300   7.600   8.096   9.000  25.900
```
看平均数和中位数，毫无疑问应该谈判要求加薪。至于，要求加一点儿？大幅度加？按平均数/中位数？就看你自己了。
