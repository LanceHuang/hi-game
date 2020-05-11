package com.lance.game.orm;

import com.lance.game.orm.dao.ITestConfigDao;
import com.lance.game.orm.model.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MongoDaoTest.class)
@ComponentScan("com.lance.game.orm.dao")
public class MongoDaoTest {

    @Resource
    private ITestConfigDao testConfigDao;

    @Test
    public void test() {
        TestConfig testConfig = new TestConfig();
        testConfig.setId(1);
        testConfig.setName("Lance");
        testConfig.setAge(998);
        testConfigDao.addTestConfig(testConfig); // C
        System.out.println(testConfigDao.getTestConfigById(1)); // R

        testConfig.setName("Leo");
        testConfigDao.replaceTestConfig(1, testConfig); // U
        System.out.println(testConfigDao.getTestConfigById(1));

        testConfigDao.deleteTestConfig(1); // D
    }
}
