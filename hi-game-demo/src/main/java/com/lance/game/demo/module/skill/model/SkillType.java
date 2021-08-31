package com.lance.game.demo.module.skill.model;

/**
 * 技能类型
 *
 * @author Lance
 */
public enum SkillType {

    /** 普通技能 */
    COMMON(1) {
        @Override
        public Skill create() {
            return new CommonSkill();
        }
    },
    ;

    private int type;

    SkillType(int type) {
        this.type = type;
    }

    public abstract Skill create();

    public static SkillType typeOf(int t) {
        for (SkillType skillType : values()) {
            if (skillType.type == t) {
                return skillType;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }
}
