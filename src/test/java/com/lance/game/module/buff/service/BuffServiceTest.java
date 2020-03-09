package com.lance.game.module.buff.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BuffServiceTest.class)
@ComponentScan({"com.lance.game.module.buff"})
public class BuffServiceTest {

    @Resource
    private IBuffService buffService;

    @Test
    public void createBuff() {
        System.out.println(buffService.createBuff(1));
        System.out.println(buffService.createBuff(2));
    }
}