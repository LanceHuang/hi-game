package com.lance.game.module.skill.model;

import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.config.SkillConfig;
import lombok.Data;

/**
 * 技能实体
 *
 * @author Lance
 */
@Data
public abstract class AbstractSkill {

    private int id;
    private int type;
    private int level = 1;

    public void init(SkillConfig skillConfig) {
        this.id = skillConfig.getId();
        this.type = skillConfig.getType();
    }

    /**
     * 使用技能
     */
    public void use(Player player) {
        // todo
    }
}
