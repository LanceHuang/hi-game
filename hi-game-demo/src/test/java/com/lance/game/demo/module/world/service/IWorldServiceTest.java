package com.lance.game.demo.module.world.service;

import com.lance.game.demo.module.player.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IWorldServiceTest.class)
@ComponentScan("com.lance.game.demo.module.world")
public class IWorldServiceTest {

    @Resource
    private IWorldService worldService;

    @Before
    public void before() {
        worldService.init();
    }

    @Test
    public void enterMap() {
        Player mockPlayer = new Player();
        mockPlayer.setAccount("Lance");

        Random random = new Random();
        int[] mapIds = {10083, 10084, 10085, 10086, 10087};

        for (int i = 0; i <= 150; i++) {
            int mapId = mapIds[random.nextInt(mapIds.length)];
            worldService.enterMap(mockPlayer, mapId);
        }
    }
}