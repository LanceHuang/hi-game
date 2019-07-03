package com.lance.game.module.skill.service;

import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.model.SkillEntry;

/**
 * @author Lance
 * @since 2019/7/2 17:27
 */
public interface ISkillService {

    void learnSkill(Player player, int skillId, int skillLevel);

    void forgetSkill(Player player, int skillId);

    SkillEntry querySkill(Player player, int skillId);

    boolean hasSkill(Player player, int skillId);
}
