package com.lance.game.demo.module.skill.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 技能容器
 *
 * @author Lance
 * @since 2021/8/31
 */
public class SkillContainer {

    private Map<Integer, Skill> skillMap = new HashMap<>();

    public static SkillContainer valueOf() {
        return new SkillContainer();
    }

    public Skill addSkill(Skill skill) {
        return this.skillMap.put(skill.getId(), skill);
    }

    public Skill removeSkill(int skillId) {
        return this.skillMap.remove(skillId);
    }

    public Skill getSkill(int skillId) {
        return this.skillMap.get(skillId);
    }

    public boolean containsSkill(int skillId) {
        return this.skillMap.containsKey(skillId);
    }
}
