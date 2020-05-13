package com.lance.game.orm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MongoDaoScannerTest.class)
@Configuration
public class MongoDaoScannerTest {

    @Bean
    public MongoDaoScanner mongoDaoScanner() {
        MongoDaoScanner mongoDaoScanner = new MongoDaoScanner();
        mongoDaoScanner.setBasePackage("com.lance.game.orm.dao");
        return mongoDaoScanner;
    }

    @Test
    public void test() {
        System.out.println("Hello world");
    }

}