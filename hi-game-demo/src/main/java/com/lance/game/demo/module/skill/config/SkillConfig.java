package com.lance.game.demo.module.skill.config;

/**
 * 技能配置（不需要技能等级，属性按公式生成）
 *
 * @author Lance
 * @since 2019/7/2 17:31
 */
public class SkillConfig {

    /** 技能id */
    private int id;
    /** 技能类型 */
    private int type;
    /** 技能id */
    private int name;

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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
