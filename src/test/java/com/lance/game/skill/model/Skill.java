package com.lance.game.skill.model;

/**
 * @author Lance
 * @since 2019/2/26 15:24
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int[] getAhead() {
        return ahead;
    }

    public void setAhead(int[] ahead) {
        this.ahead = ahead;
    }
}
