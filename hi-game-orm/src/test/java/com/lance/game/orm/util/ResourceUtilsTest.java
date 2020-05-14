package com.lance.game.orm.util;

import org.junit.jupiter.api.Test;

class ResourceUtilsTest {

    @Test
    void resolvePackage() {
        for (Class<?> clazz : ResourceUtils.resolvePackage("com.lance.game.orm.util")) {
            System.out.println(clazz);
        }
        System.out.println();
        for (Class<?> clazz : ResourceUtils.resolvePackage("com.lance.game.orm.util", Class::isInterface)) {
            System.out.println(clazz);
        }
    }
}