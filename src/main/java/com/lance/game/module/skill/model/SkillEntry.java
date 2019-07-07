package com.lance.game.module.skill.model;

import com.lance.game.module.skill.config.SkillConfig;
import lombok.Data;

/**
 * 技能条目
 *
 * @author Lance
 * @since 2019/7/2 17:38
 */
@Data
public class SkillEntry {

    private int skillId;
    private int level;
    //todo 熟练度？

    public static SkillEntry valueOf(SkillConfig skillConfig) {
        SkillEntry skillEntry = new SkillEntry();
        skillEntry.skillId = skillConfig.getSkillId();
        skillEntry.level = skillConfig.getLevel();
        return skillEntry;
    }
}
