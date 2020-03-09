package com.lance.game.module.skill.config;

import lombok.Data;

/**
 * 技能配置（不需要技能等级，属性按公式生成）
 *
 * @author Lance
 * @since 2019/7/2 17:31
 */
@Data
public class SkillConfig {

    /** 技能id */
    private int id;
    /** 技能类型 */
    private int type;
    /** 技能id */
    private int name;

}
