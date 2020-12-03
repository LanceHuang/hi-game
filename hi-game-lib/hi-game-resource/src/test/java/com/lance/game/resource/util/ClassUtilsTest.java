package com.lance.game.resource.util;

import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2020/12/3
 */
class ClassUtilsTest {

    @Test
    void test() {
        String basePackage = "com.lance.game.resource.reader,com.lance.game.resource.util";
        ClassUtils.scan(basePackage, Class::isInterface).forEach(System.out::println);
    }
}