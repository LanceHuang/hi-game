> 仿MyBatis，做个持久层

### 设计思路
1. 连接/关闭由容器管理
2. 模仿MyBatis生成代理类，Dao代码自动生成，所有业务操作都放到MongoUtils
3. 用DocumentHandler转换对象和文档
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
ItemManager -> ItemDao$Proxy -> MongoUtils
            -> ItemTypeDao$Proxy -> MongoUtils
TestDaoManager -> TestConfigDao$Proxy -> MongoUtils
```

**第三阶段**：自动生成映射
```
ItemManager -> IItemDao -> ItemDao$Proxy + ItemDocumentHandler$Proxy -> MongoUtils
            -> IItemTypeDao -> ItemTypeDao$Proxy + ItemTypeDocumentHandler$Proxy -> MongoUtils
TestDaoManager -> ITestConfigDao -> TestConfigDao$Proxy + TestConfigDocumentHandler$Proxy -> MongoUtils
```

**第四阶段**：仿MyBatis，做个MapperScannerConfigurer，将代理Dao和代理DocumentHandler注册到Spring容器  

**第五阶段**：考虑使用连接池

### 小想法
1. 为什么不像MyBatis那样，直接在$Enhancer中调用MapperProxy？
MyBatis将sql、model等都配置在.xml，运行时读取配置并注册到容器，不像Hibernate那样自动生成sql，所以需要用MapperProxy转发请求。
MongoDB没有sql语句，操作相对固定，可以自动生成，没必要配到.xml，可以在$Enhancer直接调用MongoUtils。