package com.lance.game.demo.module.player.service;

import com.lance.game.demo.module.player.constant.PlayerConstant;
import com.lance.game.demo.module.player.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IPlayerServiceTest.class)
@ComponentScan({"com.lance.game.demo.module.player"})
public class IPlayerServiceTest {

    @Resource
    private IPlayerService playerService;

    @Test
    public void savePlayer() {
        Player player = new Player();
        player.setId(546422L);
        player.setAccount("lance");
        player.setNickname("奥利giao");
        player.setGender(PlayerConstant.GENDER_MAN);
        player.setLevel(999);
        player.setExp(4455300);
        playerService.savePlayer(player);

        System.out.println(player);
        System.out.println(playerService.getPlayer(546422L));
    }

    @Test
    public void getPlayer() {
    }
}