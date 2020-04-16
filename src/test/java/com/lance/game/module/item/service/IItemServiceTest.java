package com.lance.game.module.item.service;

import com.lance.game.module.item.model.AbstractItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IItemServiceTest.class)
@ComponentScan({"com.lance.game.module.item"})
public class IItemServiceTest {

    @Resource
    private IItemService itemService;

    @Test
    public void test() {
        AbstractItem item = itemService.createItem(1);
        System.out.println(item);
    }


}