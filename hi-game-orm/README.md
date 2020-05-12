> 仿MyBatis，做个持久层

### 设计思路
1. 连接/关闭由容器管理
2. 所有CRUD操作都自动生成，模仿MyBatis生成代理类，所有业务操作都放到MongoUtils
3. 用DocumentHandler将转换对象和文档
4. 模拟DataSource设计一个连接池

由于没办法一步到位，先分阶段设计

**第一阶段**：最原始的操作方式
```
ItemManager -> ItemDao -> MongoUtils
            -> ItemTypeDao -> MongoUtils
TestDaoManager -> TestConfigDao -> MongoUtils

class MongoUtils
    insert
    find
    delete
```

**第二阶段**：统一的API
```
ItemManager -> ItemDao$Enhancer -> MongoUtils
            -> ItemTypeDao$Enhancer -> MongoUtils
TestDaoManager -> TestConfigDao$Enhancer -> MongoUtils
```

### 小想法
1. 为什么不像MyBatis那样，直接在$Enhancer中调用MapperProxy？
MyBatis将sql、model等都配置在.xml，运行时读取配置并注册到容器，不像Hibernate那样自动生成sql，所以需要用MapperProxy转发请求。
MongoDB没有sql语句，操作相对固定，可以自动生成，没必要配到.xml，所以可以在$Enhancer直接调用MongoUtils。