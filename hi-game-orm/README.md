仿MyBatis，做个持久层

### 涉及内容
1. 连接/关闭
2. insert/find/replace/delete
3. orm
4. 连接池

### 设计思路
1. 连接/关闭由容器管理
2. 所有操作都自动生成，模仿MyBatis生成代理类，所有业务操作都放到MongoUtils
3. 自动生成DocumentHandler
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
ItemManager -> ItemDao$Proxy -> MongoMethodInvoker -> MongoUtils
            -> ItemTypeDao$Proxy -> MongoMethodInvoker -> MongoUtils
TestDaoManager -> TestConfigDao$Proxy -> MongoMethodInvoker -> MongoUtils
```


### 其他
* manager：用于集中管理dao
* dao：跟MyBatis类似，用反射生成具体类