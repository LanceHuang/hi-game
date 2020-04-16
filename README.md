> 结合公司的游戏项目构思的游戏框架，支持SLG

[toc]

---


### 游戏项目特点
（1）长连接
（2）网络 >> IO
（3）二进制数据传输
（4）频繁加功能

### 架构
小服设计
```
+-------------------------------------------+
| 客户端          LayaAir/U3d                |
+-------------------------------------------+
| 网络层          Netty                      |
+-------------------------------------------+
|                            |              |
| 业务层                     | 事件总线       |
|                            |              |
+-------------------------------------------+
| 持久层          Hibernate      缓存        |
+-------------------------------------------+
```


跨服战设计
```
Client1                      Client2
   |



Server1                      Server1

```


### 日志目录结构
```
logs
    game.log
    error.log
module-logs
    2020-04-01
        test.log
    2020-04-02
        buff.log
        test.log
```

小服的话，这样比较方便查问题，只需要配置浏览器访问（我之前的项目有6w多个服）
```
http://log.lance.com/S564/logs/game.log
http://log.lance.com/S81313/module-logs/2020-04-05/item.log
```

大服，或者觉得这样运维比较麻烦的话，还可以试试搭ELK，然后把业务日志按日期输出


# 问题

### 游戏项目与JavaWeb项目有什么异同？
* 相同：
* 不同：游戏项目中经常用到富血模型

### 为什么用数字实现i18n？而不用字符串？（module.function.messageType）
我比较倾向于用字符串，字符串简单直观，所见即所得。
但做游戏项目时，策划并不懂i18n字符串，他们只懂配置文字信息，如：“暴击伤害提高10%”。
用数字的话，策划配置文字信息后，可以随便定义一个新的数字，不需要花心思去想i18n字符串。
个人开发可以用数字，也可以用字符串；如果i18n信息需要三方提供，推荐还是用数字。
```
1568 暴击伤害提高10%
damage.addition.cri 暴击伤害提高10%
```

### 代码中为什么要用专门管理i18n的类？（I18nId.ERROR，I18nId.ERROR_LOGIN_FAIL）
提高代码可读性，你肯定不想在代码里到处写魔法数

### 如何保证服务端客户端战斗结果一致？
战斗大致分为三类：
* 服务端战报：服务端计算完整场战斗，然后发给客户端播放；或者服务端可以算秒伤
* 客户端战斗：服务端刷怪，客户端击杀怪后，服务端发奖
* 客户端+服务端：客户端可操作，服务端负责验证

### 游戏配置配在数据库？还是文件？
数据库：
* 好处：查数据比较方便
* 坏处：不方便更改数据库字段类型（只能新增字段）；有些配置只有服务端用到，而且客户端不方便读数据库；搭新服务器时，需要预先上传配置

文件：
* 好处：开发阶段，策划可频繁改字段类型；后期维护，策划改类型后，线上数据库不需要更新
* 坏处：服务端程序包大

### 为什么要用IBuffFactory创建buff，而不直接写在BuffType里？
* java的enum实现方法，会产生大量内部类，不便于以后线上热更
* 其他编程语言没有这种逻辑，抽象工厂便于理解

### 为什么不用lombok？
* 这是开源的库，没必要所有人都安装lombok插件
* 不希望自动生成某些成员变量的getter/setter

### 为什么不再使用com.lance.game.util.Assert？
我在两个项目中用过，有助于减少代码量，但是不便于阅读，主要是项目组其他成员不适应

### 为什么不再使用valueOf来创建BuffContainer等实体？
以String.valueOf为例，表示用valueOf的参数去构建实体。创建BuffContainer时是直接new一个新的，没有涉及到一些特殊的处理。
valueOf使用场景：
* 用一些特殊参数去构建实体
* 涉及到复杂的初始化操作

### 为什么要区分普通日志和业务日志？
* 普通日志用于快速定位问题，项目上线后，开发者只需要盯着这个日志，就知道是否出现了严重问题
* 业务日志用于记录业务流程，保证业务流程的正常运行，当特定业务模块出问题时，才需要用到

两者结合可以快速定位问题

### 为什么LoggerUtil打印业务日志时，需要用到LogModule？
* 不同于web等项目，游戏业务模块经常增加（迭代快的项目，平均一周增加一个模块），不推荐添加所有的模块的包名到log4j.properties
* GameLoggerFactory.getLogger(LogModule.BUFF)这种格式比较难读，可以利用java的enum特性LoggerUtil.log(LogModule.BFF)提高可读性

### 为什么要用LogCode？
若需要判断玩家怎么获得buffId=4600，仅通过业务日志，无法判断。
添加code之后，可以知道添加buff的原因

线上一般都是用awk按逗号切割日志，这样可以快速定位到具体的玩家，以及因1001而添加的buff
```
time:1584986783941,code:1001,account:lance,id:1,startTime:1584986783938,duration:10000,endTime:1584986793938 添加buff

cat buff.log|grep code=10001|awk -F ',' 'print $3'|sort|uniq
```

如果搭建了ELK，还可以用ES做聚合分析

### 为什么要用MongoDB？
这阵子我一直头疼以下问题：
* 怎么让策划更好地配表？策划不懂数据库
* 怎么读取配置数据？配在excel的话，还要搞个读取工具
* 怎么快速开发？不想在开发时建表、改表
* 用Hibernate？还是MyBatis？Hibernate要专门去学，MyBatis的话不适合越来越多的表
* 怎么使用Hibernate的自动建表功能？为此我还要专门去学Hibernate或MyBatis Plus
* 以后数据量大了，我又该怎么扩展？哪些数据需要分库分表？
* 不希望经常写回库，又怎么设计缓存工具、定时回写工具？

问题太多太多了，直接导致我三周不想写代码，直到我遇上MongoDB。

* MongoDB每个文档都是json，支持稀疏矩阵，药水和武器可以配在一个集合
```
game.item

# 药水
{
    type : 1,
    value : 56411
}

# 武器
{
    type : 4,
    atk : 896
}
```
* MongoDB存储json的话，就可以不考虑三范式了，减少表数量，也不需要建库、建表（自动建库、建表）
* 听说可以添加分片，横向扩容

虽然解决不了所有问题，但在一定程度上减少了很多工作量，真香

