package com.lance.game.orm;

import com.lance.game.orm.dao.ITestConfigDao;
import com.lance.game.orm.model.TestConfig;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

class MongoDaoEnhancerTest {

    @Test
    public void test() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        Class<?> enhanceClass = MongoDaoEnhancer.enhanceClass(ITestConfigDao.class);
        System.out.println(enhanceClass);
        System.out.println(enhanceClass.getInterfaces()[0]);
        System.out.println(enhanceClass.getDeclaredMethods()[0]);

        ITestConfigDao testConfigDao = (ITestConfigDao) enhanceClass.newInstance();
        System.out.println(testConfigDao);


        TestConfig testConfig = new TestConfig();
        testConfig.setId(5);
        testConfig.setName("Alice");
        testConfig.setAge(22);
        testConfigDao.addTestConfig(testConfig);
        testConfigDao.addTestConfigs(null);
    }

}