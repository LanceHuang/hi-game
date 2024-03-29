package com.lance.game.demo.module.skill.service;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.skill.config.SkillConfig;
import com.lance.game.demo.module.skill.manager.SkillManager;
import com.lance.game.demo.module.skill.model.Skill;
import com.lance.game.demo.module.skill.model.SkillContainer;
import com.lance.game.demo.module.skill.model.SkillType;
import com.lance.game.demo.util.Assert;
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
    public void learnSkill(Player player, int id, int level) {
        Assert.notNull(player, I18nId.ERROR);
        SkillContainer skillContainer = player.getSkillContainer();
        Assert.notNull(skillContainer, I18nId.ERROR);

        Skill skill = createSkill(player, id);
        skillContainer.addSkill(skill);

        //todo Do sth321
    }

    private Skill createSkill(Player player, int id) {
        SkillConfig skillConfig = skillManager.getSkillConfig(id);
        Assert.notNull(skillConfig, I18nId.ERROR);
        SkillType skillType = SkillType.typeOf(skillConfig.getType());
        Assert.notNull(skillType, I18nId.ERROR);

        Skill skill = skillType.create();
//        skill.init(skillConfig);
        return skill;
    }

    @Override
    public void forgetSkill(Player player, int id) {
        Assert.notNull(player, I18nId.ERROR);
        SkillContainer skillContainer = player.getSkillContainer();
        if (null == skillContainer) {
            return;
        }

//        SkillEntry oldSkillEntry = skillContainer.removeSkill(id);
        //todo Do sth
    }

    @Override
    public void useSkill(Player player, int id) {
        // todo
    }

    @Override
    public Skill getSkill(Player player, int id) {
        Assert.notNull(player, I18nId.ERROR);

        SkillContainer skillContainer = player.getSkillContainer();
        return skillContainer == null ? null : skillContainer.getSkill(id);
    }

    @Override
    public boolean containsSkill(Player player, int id) {
        Assert.notNull(player, I18nId.ERROR);

        SkillContainer skillContainer = player.getSkillContainer();
//        return skillContainer != null && skillContainer.hasSkill(id);
        return false;
    }
}
