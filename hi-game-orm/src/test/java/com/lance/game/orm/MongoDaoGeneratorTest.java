package com.lance.game.orm;

import com.lance.game.orm.dao.INewTestConfigDao;
import com.lance.game.orm.model.TestConfig;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

class MongoDaoGeneratorTest {

    @Test
    public void test() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        Class<?> enhanceClass = MongoDaoGenerator.generateProxyClass(INewTestConfigDao.class);
        System.out.println(enhanceClass);
        System.out.println(enhanceClass.getInterfaces()[0]);
//        System.out.println(enhanceClass.getDeclaredMethods()[0]);

        INewTestConfigDao newTestConfigDao = (INewTestConfigDao) enhanceClass.newInstance();
        System.out.println(newTestConfigDao);

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