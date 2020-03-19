package com.lance.game.module.skill.model;

import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.config.SkillConfig;

/**
 * 技能实体
 *
 * @author Lance
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
