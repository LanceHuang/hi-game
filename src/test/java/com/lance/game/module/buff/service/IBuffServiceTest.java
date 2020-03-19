package com.lance.game.module.buff.service;

import com.lance.game.module.buff.model.BuffContainer;
import com.lance.game.module.player.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IBuffServiceTest.class)
@ComponentScan({"com.lance.game.module.buff"})
public class IBuffServiceTest {

    @Resource
    private IBuffService buffService;

    @Test
    public void createBuff() {
        System.out.println(buffService.createBuff(1));
        System.out.println(buffService.createBuff(2));
    }

    @Test
    public void createAndAddBuff() {
        Player player = createTestPlayer();
        buffService.createAndAddBuff(player, 1);
    }

    @Test
    public void removeBuff() {
        Player player = createTestPlayer();
        Assert.assertFalse(buffService.containsBuff(player, 1));
        buffService.createAndAddBuff(player, 1);
        Assert.assertTrue(buffService.containsBuff(player, 1));
        buffService.removeBuff(player, 1, "测试删buff");
        Assert.assertFalse(buffService.containsBuff(player, 1));
    }

    @Test
    public void containsBuff() {
        Player player = createTestPlayer();
        Assert.assertFalse(buffService.containsBuff(player, 1));
        buffService.createAndAddBuff(player, 1);
        Assert.assertTrue(buffService.containsBuff(player, 1));
    }

    private Player createTestPlayer() {
        Player player = new Player();
        player.setAccount("lance");
        BuffContainer buffContainer = new BuffContainer();
        buffContainer.setOwner(player);
        player.setBuffContainer(buffContainer);
        return player;
    }
}