package com.lance.game.orm;

import com.lance.game.orm.model.TestConfig;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

class DocumentHandlerGeneratorTest {

    @Test
    public void test() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        Class<?> aClass = DocumentHandlerGenerator.generateClass(TestConfig.class);
        System.out.println(aClass);
        System.out.println(aClass.getInterfaces()[0]);

        DocumentHandler<TestConfig> documentHandler = (DocumentHandler<TestConfig>) aClass.newInstance();
        System.out.println(documentHandler);

        TestConfig testConfig = new TestConfig();
        testConfig.setId(10);
        testConfig.setName("Lance");
        testConfig.setAge(25);
        System.out.println(documentHandler.parse(testConfig));
        TestConfig newTestConfig = documentHandler.handle(documentHandler.parse(testConfig));
        System.out.println(newTestConfig);
    }

}