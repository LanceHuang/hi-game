结合公司的游戏项目构思的游戏框架，写着玩玩


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
| 缓存                                       |
+-------------------------------------------+
| 持久层          Hibernate                  |
+-------------------------------------------+

```


跨服战设计
```
Client1                      Client2
   |



Server1                      Server1

```



### 问题

（1）游戏项目与JavaWeb项目有什么异同？
* 相同：
* 不同：游戏项目中经常用到富血模型

（2）为什么用数字实现i18n？而不用字符串？（module.function.messageType）
```
我比较倾向于用字符串，字符串简单直观，所见即所得。
但做游戏项目时，策划并不懂i18n字符串，他们只懂配置文字信息，如：“暴击伤害提高10%”。
用数字的话，策划配置文字信息后，可以随便定义一个新的数字，不需要花心思去想i18n字符串。

1568 暴击伤害提高10%
damage.addition.cri 暴击伤害提高10%

个人开发可以用数字，也可以用字符串；如果i18n信息需要三方提供，推荐还是用数字
```

（3）代码中为什么要用专门管理i18n的类？（I18nId.ERROR，I18nId.ERROR_LOGIN_FAIL）
```
提高代码可读性
```


### 术语
* 条目：entry，玩家运行时使用的可变数据
* 配置：config，静态配置的数据
* 容器：container，拥有自我管理能力的实例对象，富血模型
