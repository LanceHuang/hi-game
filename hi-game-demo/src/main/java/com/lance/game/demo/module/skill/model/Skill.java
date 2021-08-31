package com.lance.game.demo.module.skill.model;

import lombok.Data;

/**
 * 技能实体
 *
 * @author Lance
 * @since 2021/8/31
 */
@Data
public abstract class Skill {

    protected int id;

    protected int level;
}
