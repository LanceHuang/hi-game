package com.lance.game.module.skill.model;

/**
 * 技能类型
 *
 * @author Lance
 */
public enum SkillType {

    /** 主动技能 */
    COMMON(1) {
        @Override
        public AbstractSkill create() {
            return new CommonSkill();
        }
    },
    /** 被动技能 */
    PASSIVITY(2) {
        @Override
        public AbstractSkill create() {
            return new PassivitySkill();
        }
    };

    private int type;

    SkillType(int type) {
        this.type = type;
    }

    public abstract AbstractSkill create();

    // todo 主动技能直接使用，被动技能相当于buff，施加debuff相当于隔一段时间后激活持有者的buff

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
