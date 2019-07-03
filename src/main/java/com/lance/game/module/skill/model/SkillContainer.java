package com.lance.game.module.skill.model;

import lombok.Data;

import java.util.Map;

/**
 * 技能容器
 *
 * @author Lance
 * @since 2019/7/2 17:28
 */
@Data
public class SkillContainer {

    private Map<Integer, SkillEntry> skills;

    public SkillEntry addSkill(SkillEntry skillEntry) {
        return skills.put(skillEntry.getSkillId(), skillEntry);
    }

    public SkillEntry removeSkill(int skillId) {
        return skills.remove(skillId);
    }

    public SkillEntry getSkill(int skillId) {
        return skills.get(skillId);
    }

    public boolean hasSkill(int skillId) {
        return skills.containsKey(skillId);
    }
}
