package com.lance.game.module.skill.model;

/**
 * @author Lance
 * @since 2019/7/2 20:22
 */
public enum SkillType {
    /** 主动技能 */
    ACTIVE,
    /** 被动技能 */
    PASSIVE;

    // todo 主动技能直接使用，被动技能相当于buff，施加debuff相当于隔一段时间后激活持有者的buff
}
