package com.lance.game.module.skill.service;


import com.lance.game.constant.I18nId;
import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.config.SkillConfig;
import com.lance.game.module.skill.manager.SkillManager;
import com.lance.game.module.skill.model.AbstractSkill;
import com.lance.game.module.skill.model.SkillContainer;
//import com.lance.game.module.skill.model.SkillEntry;
import com.lance.game.module.skill.model.SkillType;
import com.lance.game.util.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class SkillService implements ISkillService {

    @Resource
    private SkillManager skillManager;

    @Override
    public void addSkill(Player player, int id, int level) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        Assert.assertNotNull(skillContainer);

        AbstractSkill skill = createSkill(player, id);
        skillContainer.addSkill(skill);

        //todo Do sth321
    }

    private AbstractSkill createSkill(Player player, int id) {
        SkillConfig skillConfig = skillManager.getSkillConfig(id);
        Assert.assertNotNull(skillConfig);
        SkillType skillType = SkillType.typeOf(skillConfig.getType());
        Assert.assertNotNull(skillType);

        AbstractSkill skill = skillType.create();
        skill.init(skillConfig);
        return skill;
    }

    @Override
    public void removeSkill(Player player, int id) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        if (null == skillContainer) {
            return;
        }

//        SkillEntry oldSkillEntry = skillContainer.removeSkill(id);
        //todo Do sth
    }

    @Override
    public AbstractSkill getSkill(Player player, int id) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
        return skillContainer == null ? null : skillContainer.getSkill(id);
    }

    @Override
    public boolean containsSkill(Player player, int id) {
        Assert.assertNotNull(player);
        SkillContainer skillContainer = player.getSkillContainer();
//        return skillContainer != null && skillContainer.hasSkill(id);
        return false;
    }
}
