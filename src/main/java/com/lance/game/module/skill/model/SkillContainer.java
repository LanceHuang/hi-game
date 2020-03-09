package com.lance.game.module.skill.model;

import lombok.Data;

import java.util.Map;

/**
 * 技能容器
 *
 * @author Lance
 */
@Data
public class SkillContainer {

    private Map<Integer, AbstractSkill> skillMap;

    public static SkillContainer valueOf() {
        return new SkillContainer();
    }

    public AbstractSkill addSkill(AbstractSkill skill) {
        return this.skillMap.put(skill.getId(), skill);
    }

    public AbstractSkill removeSkill(int skillId) {
        return this.skillMap.remove(skillId);
    }

    public AbstractSkill getSkill(int skillId) {
        return this.skillMap.get(skillId);
    }

    public boolean containsSkill(int skillId) {
        return this.skillMap.containsKey(skillId);
    }
}
