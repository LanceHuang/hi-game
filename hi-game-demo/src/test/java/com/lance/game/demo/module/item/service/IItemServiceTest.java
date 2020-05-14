package com.lance.game.demo.module.item.service;

import com.lance.game.demo.module.item.model.AbstractItem;
import com.lance.game.orm.MongoDaoScanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IItemServiceTest.class)
@Configuration
@ComponentScan("com.lance.game.demo.module.item")
public class IItemServiceTest {

    @Resource
    private IItemService itemService;

    @Bean
    public MongoDaoScanner mongoDaoScanner() {
        MongoDaoScanner mongoDaoScanner = new MongoDaoScanner();
        mongoDaoScanner.setBasePackage("com.lance.game.demo.module.item.dao");
        return mongoDaoScanner;
    }

    @Test
    public void test() {
        AbstractItem item = itemService.createItem(2004);
        System.out.println(item);
        System.out.println(item.getObjectId());
        System.out.println(item.getId());
        System.out.println(item.getType());
        System.out.println(item.getNum());
    }
}