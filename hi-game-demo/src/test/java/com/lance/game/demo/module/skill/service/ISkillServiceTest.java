package com.lance.game.demo.module.skill.service;

import com.lance.game.demo.module.player.model.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ISkillServiceTest.Config.class)
public class ISkillServiceTest {

    @Resource
    private ISkillService skillService;

    @Test
    public void test() {
        int skillId = 8888;
        int skillLvl = 3;

        Player player = new Player();

        skillService.learnSkill(player, skillId, skillLvl);
        Assert.assertTrue(skillService.containsSkill(player, skillId));
        Assert.assertFalse(skillService.containsSkill(player, 8989));

        skillService.forgetSkill(player, skillId);
        Assert.assertFalse(skillService.containsSkill(player, skillId));
    }

    @SpringBootApplication(scanBasePackages = "com.lance.game.demo.module.skill")
    public static class Config {
    }
}