package com.lance.game.demo.module.skill.service;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.skill.config.SkillConfig;
import com.lance.game.demo.module.skill.manager.SkillManager;
import com.lance.game.demo.module.skill.model.AbstractSkill;
import com.lance.game.demo.module.skill.model.SkillContainer;
import com.lance.game.demo.module.skill.model.SkillType;
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
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }
        SkillContainer skillContainer = player.getSkillContainer();
        if (skillContainer == null) {
            throw new GameException(I18nId.ERROR);
        }

        AbstractSkill skill = createSkill(player, id);
        skillContainer.addSkill(skill);

        //todo Do sth321
    }

    private AbstractSkill createSkill(Player player, int id) {
        SkillConfig skillConfig = skillManager.getSkillConfig(id);
        if (skillConfig == null) {
            throw new GameException(I18nId.ERROR);
        }
        SkillType skillType = SkillType.typeOf(skillConfig.getType());
        if (skillType == null) {
            throw new GameException(I18nId.ERROR);
        }

        AbstractSkill skill = skillType.create();
        skill.init(skillConfig);
        return skill;
    }

    @Override
    public void removeSkill(Player player, int id) {
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }
        SkillContainer skillContainer = player.getSkillContainer();
        if (null == skillContainer) {
            return;
        }

//        SkillEntry oldSkillEntry = skillContainer.removeSkill(id);
        //todo Do sth
    }

    @Override
    public AbstractSkill getSkill(Player player, int id) {
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }

        SkillContainer skillContainer = player.getSkillContainer();
        return skillContainer == null ? null : skillContainer.getSkill(id);
    }

    @Override
    public boolean containsSkill(Player player, int id) {
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }

        SkillContainer skillContainer = player.getSkillContainer();
//        return skillContainer != null && skillContainer.hasSkill(id);
        return false;
    }
}
