package com.lance.game.orm.annotation;

import com.lance.game.orm.dao.ITestConfigDao;
import com.lance.game.orm.model.TestConfig;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author Lance
 */
// todo Spring
public class MongoDaoTest {

    @Resource
    private ITestConfigDao testConfigDao;

    @Test
    public void test() {
        TestConfig testConfig = new TestConfig();
        testConfig.setId(1);
        testConfig.setName("Lance");
        testConfig.setAge(998);
        testConfigDao.saveTestConfig(testConfig);

        System.out.println(testConfigDao.getTestConfigById(1));
    }
}
