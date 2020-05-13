package com.lance.game.orm;

import com.lance.game.orm.handler.DocumentHandler;
import com.lance.game.orm.model.TestConfig;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

class DocumentHandlerProxyGeneratorTest {

    @Test
    public void test() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        Class<?> aClass = DocumentHandlerProxyGenerator.generateClass(TestConfig.class);
        System.out.println(aClass);
        System.out.println(aClass.getInterfaces()[0]);

        DocumentHandler<TestConfig> documentHandler = (DocumentHandler<TestConfig>) aClass.newInstance();
        System.out.println(documentHandler);

        TestConfig testConfig = new TestConfig();
        testConfig.setId(10);
        testConfig.setName("Lance");
        testConfig.setAge(25);
        testConfig.setTestIntPrimitive(333);
        testConfig.setTestInt(334);
        testConfig.setTestLongPrimitive(444L);
        testConfig.setTestLong(445L);
        testConfig.setTestDoublePrimitive(555D);
        testConfig.setTestDouble(556D);
        testConfig.setTestBooleanPrimitive(true);
        testConfig.setTestBoolean(false);
        System.out.println(documentHandler.parse(testConfig));
        TestConfig newTestConfig = documentHandler.handle(documentHandler.parse(testConfig));
        System.out.println(newTestConfig);
    }

}