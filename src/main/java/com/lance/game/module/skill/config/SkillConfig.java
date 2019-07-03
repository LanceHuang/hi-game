package com.lance.game.module.skill.config;

import lombok.Data;

/**
 * 技能配置。
 * 目前的设想是一个技能的多个等级记录多条数据。
 * 但是这样会产生大量的配置数据，所以也可以拆出一个SkillLevelConfig，当然也可以通过公式来实现。
 *
 * @author Lance
 * @since 2019/7/2 17:31
 */
@Data
public class SkillConfig {
    private int id;
    private int skillId;
    private int level;
    private int name;
}
