package com.lance.game.skill.model;

import lombok.Data;

/**
 * @author Lance
 * @since 2019/2/26 15:24
 */
@Data
public class Skill {

    private int    id;
    private String name;
    private int    level;
    private int[]  ahead;


    public Skill() {
    }

    public Skill(int id, String name, int level, int[] ahead) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.ahead = ahead;
    }

    public static Skill valueOf(int id, String name, int level, int[] ahead) {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skill.setLevel(level);
        skill.setAhead(ahead);
        return skill;
    }
}
