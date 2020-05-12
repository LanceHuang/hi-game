package com.lance.game.orm;

import com.lance.game.orm.model.TestConfig;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentHandlerGeneratorTest {

    @Test
    public void test() throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        Class<?> aClass = DocumentHandlerGenerator.generateClass(TestConfig.class);
        System.out.println(aClass);
        System.out.println(aClass.getInterfaces()[0]);

        DocumentHandler<TestConfig> documentHandler = (DocumentHandler<TestConfig>) aClass.newInstance();
        System.out.println(documentHandler);
        System.out.println(documentHandler.parse(null));
        System.out.println(documentHandler.handle(null));
    }

}