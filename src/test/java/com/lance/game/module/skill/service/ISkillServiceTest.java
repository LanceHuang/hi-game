package com.lance.game.module.skill.service;

import com.lance.game.module.player.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration
@ComponentScan("com.lance.game.module.skill")
public class ISkillServiceTest {

    @Resource
    private ISkillService skillService;

    @Test
    public void test() {
        int skillId = 8888;
        int skillLvl = 3;

        Player player = new Player();

        skillService.addSkill(player, skillId, skillLvl);
        Assert.assertTrue(skillService.containsSkill(player, skillId));
        Assert.assertFalse(skillService.containsSkill(player, 8989));

        skillService.removeSkill(player, skillId);
        Assert.assertFalse(skillService.containsSkill(player, skillId));
    }
}