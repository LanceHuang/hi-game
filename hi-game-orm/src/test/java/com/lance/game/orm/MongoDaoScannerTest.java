package com.lance.game.orm;

import com.lance.game.orm.dao.INewTestConfigDao;
import com.lance.game.orm.model.TestConfig;
import com.lance.game.orm.runner.DefaultMongoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MongoDaoScannerTest.class)
@Configuration
public class MongoDaoScannerTest {

    @Resource
    private INewTestConfigDao newTestConfigDao;

    @Bean(initMethod = "init", destroyMethod = "close")
    public PooledMongoDataSource mongoDataSource() {
        PooledMongoDataSource mongoDataSource = new PooledMongoDataSource();
        mongoDataSource.setConnectionString("mongodb://localhost:27017");
        mongoDataSource.setMaxActive(3);
        return mongoDataSource;
    }

    @Bean
    public DefaultMongoRunner defaultMongoRunner(MongoDataSource mongoDataSource) {
        return new DefaultMongoRunner(mongoDataSource);
    }

    @Bean
    public MongoDaoScanner mongoDaoScanner() {
        MongoDaoScanner mongoDaoScanner = new MongoDaoScanner();
        mongoDaoScanner.setBasePackage("com.lance.game.orm.dao");
        return mongoDaoScanner;
    }

    @Test
    public void test() {
        TestConfig testConfig = new TestConfig();
        testConfig.setId(1);
        testConfig.setName("Lance");
        testConfig.setAge(25);
        newTestConfigDao.addTestConfig(testConfig); // C

        TestConfig testConfig2 = new TestConfig();
        testConfig2.setId(2);
        testConfig2.setName("alice");
        testConfig2.setAge(25);
        newTestConfigDao.addTestConfig(testConfig2); // C

        System.out.println(newTestConfigDao.getTestConfig("{id:1}")); // R
        System.out.println(newTestConfigDao.getTestConfigs("{age:25}")); // R
        System.out.println(newTestConfigDao.countTestConfig(null));

        testConfig.setName("Leo");
        newTestConfigDao.replaceTestConfig("{id:1}", testConfig); // U
        System.out.println(newTestConfigDao.getTestConfig("{id:1}"));

        newTestConfigDao.deleteTestConfig("{id:1}"); // D
        System.out.println(newTestConfigDao.getTestConfig("{id:2}"));
        newTestConfigDao.deleteTestConfigs("{age:25}"); // D
    }

}