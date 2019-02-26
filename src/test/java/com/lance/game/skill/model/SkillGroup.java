package com.lance.game.skill.model;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Lance
 * @since 2019/2/26 15:24
 */
@Data
public class SkillGroup {

    private Map<Integer, Skill> skillMap = new TreeMap<>();

    public void addSkill(Skill skill) {
        skillMap.put(skill.getId(), skill);
    }
}
