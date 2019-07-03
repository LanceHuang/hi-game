package com.lance.game.module.skill.service;

import com.lance.common.tool.util.Assert;
import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.config.SkillConfig;
import com.lance.game.module.skill.manager.SkillManager;
import com.lance.game.module.skill.model.SkillContainer;
import com.lance.game.module.skill.model.SkillEntry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2019/7/2 18:00
 */
@Service
public class SkillService implements ISkillService {

    @Resource
    private SkillManager skillManager;

    @Override
    public void learnSkill(Player player, int skillId, int skillLevel) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        Assert.assertNotNull(skillContainer);
        SkillConfig skillConfig = skillManager.getSkillConfig(skillId, skillLevel);
        Assert.assertNotNull(skillConfig);

        SkillEntry skillEntry = SkillEntry.valueOf(skillConfig);
        SkillEntry oldSkillEntry = skillContainer.addSkill(skillEntry);
        //todo Do sth
    }

    @Override
    public void forgetSkill(Player player, int skillId) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        if (null == skillContainer) {
            return;
        }

        SkillEntry oldSkillEntry = skillContainer.removeSkill(skillId);
        //todo Do sth
    }

    @Override
    public SkillEntry querySkill(Player player, int skillId) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        return skillContainer == null ? null : skillContainer.getSkill(skillId);
    }

    @Override
    public boolean hasSkill(Player player, int skillId) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        return skillContainer != null && skillContainer.hasSkill(skillId);
    }
}
