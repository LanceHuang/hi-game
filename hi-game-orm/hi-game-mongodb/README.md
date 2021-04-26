> 仿MyBatis，做个持久层，专门用于处理Mongo

### 框架设计
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

**第四阶段**：仿MyBatis，做个MapperScannerConfigurer，将代理Dao注册到Spring容器  

**第五阶段**：考虑使用连接池

### 连接池设计
分阶段设计：

**第一阶段**：每次使用都create 
```
MongoClient mongoClient = MongoUtils.getMongoClient();
// do sth
MongoUtils.close(mongoClient);
``` 

**第二阶段**：从MongoDataSource中获取
```
MongoClient mongoClient = this.mongoDataSource.getMongoClient();
// do sth
mongoClient.close();

class XXMongoDataSource {
    
    @Override
    public MongoClient getMongoClient() {
        return MongoUtils.getMongoClient();
    }
}
```

**第三阶段**：控制连接数，暂时不考虑回收问题
```
class XXMongoDataSource {
    
    private int maxActive;

    private int activeCount;
    
    @Override
    public MongoClient getMongoClient() {
        if (this.activeCount >= this.maxActive) {
            return null;
        }
        return MongoUtils.getMongoClient();
    }
}
```

**第四阶段**：连接回收
```
class XXMongoDataSource {
    
    /** 连接池 */
    private MongoClient[] clientPool;

    /** 连接池连接数 */
    private int pooledCount;
    
    // ...
    
    @Override
    public MongoClient getMongoClient() {
        // ...

        return MongoUtils.getMongoClient();
    }

    public void recycle(MongoClientWrapper mongoClientWrapper) {
        this.lock.lock();
        try {
            this.clientPool[this.pooledCount++] = mongoClientWrapper.getMongoClient();
            this.activeCount--;
        } finally {
            this.lock.lock();
        }
    }
}

class MongoClientWrapper {

    private MongoClient mongoClient;
    
    private DefaultMongoDataSource mongoDataSource;
    
    public MongoClientWrapper(MongoClient mongoClient, DefaultMongoDataSource mongoDataSource) {
        this.mongoClient = mongoClient;
        this.mongoDataSource = mongoDataSource;
    }
    
    @Override
    public void close() {
        // ...

        this.mongoDataSource.recycle(this);

        this.mongoClient = null;
        this.mongoDataSource = null;
    }

    // ...
}
```

**第五阶段**：引入MongoDB数据源  
```
class MongoUtils {

    public static MongoClient getClient() {
        return mongoDatasource.getClient();
    }
}
```

**第五阶段**：移除MongoUtils
```
class MongoRunner {
    
    private MongoDataSource mongoDataSource;

    protected MongoClient getClient() {
        return this.mongoDataSource.getClient();
    }

    public <T> void insertOne(String databaseName, String collectionName, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(documentHandler.parse(data));
        close(client);
    }

    // ...
}

```

### 框架用法
1. 创建实体
```
public class TestConfig {

    private int id;
    private String name;
    private int age;

    //... 省略
}
```

2. 创建DAO接口，标注相应的注解
```
@MongoDao(databaseName = "db_mongo_orm", collectionName = "c_test_config", modelClass = TestConfig.class)
public interface INewTestConfigDao {

    @InsertOne
    void addTestConfig(TestConfig testConfig);

    @FindOne
    TestConfig getTestConfig(String filter);

    @FindMany
    List<TestConfig> getTestConfigs(String filter);

    @DeleteOne
    void deleteTestConfig(String filter);
}
```

3. 配置数据源，跟c3p0类似
```
@Bean(initMethod = "init", destroyMethod = "close")
public PooledMongoDataSource mongoDataSource() {
    PooledMongoDataSource mongoDataSource = new PooledMongoDataSource();
    mongoDataSource.setConnectionString("mongodb://localhost:27017");
    mongoDataSource.setMaxActive(3);
    return mongoDataSource;
}
```

4. 配置Runner，类似于MyBatis的SqlSessionFactory
```
@Bean
public DefaultMongoRunner defaultMongoRunner(MongoDataSource mongoDataSource) {
    return new DefaultMongoRunner(mongoDataSource);
}
```

5. 将MongoDaoScanner添加到Spring容器，设置basePackage，用于扫描DAO接口
```
@Bean
public MongoDaoScanner mongoDaoScanner() {
    MongoDaoScanner mongoDaoScanner = new MongoDaoScanner();
    mongoDaoScanner.setBasePackage("com.lance.game.mongodb.dao");
    return mongoDaoScanner;
}
```

5. 注入即可使用
```
@Resource
private INewTestConfigDao newTestConfigDao;
```

如果不使用Spring框架，可以只使用MongoDataSource和MongoRunner  

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


### 为什么要设计DocumentHandler？
难道你学了这么多年ORM，还想手动get/set数据？
干他丫的，json数据添加到MongoDB时，各种莫名其妙的数据类型转换异常，所以额外加了DocumentHandler.parse(T data)。
希望之后还可以像mybatis那么，做成xml配置，而且还要顺便做一下连接池。
后面还可以做成common-dbutils那样的项目


### 小想法
1. 为什么不像MyBatis那样，直接在$Enhancer中调用MapperProxy？
MyBatis将sql、model等都配置在.xml，运行时读取配置并注册到容器，不像Hibernate那样自动生成sql，所以需要用MapperProxy转发请求。
MongoDB没有sql语句，操作相对固定，可以自动生成，没必要配到.xml，可以在$Enhancer直接调用MongoUtils。