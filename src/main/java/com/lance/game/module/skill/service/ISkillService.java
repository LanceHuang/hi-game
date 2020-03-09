package com.lance.game.module.skill.service;

import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.model.AbstractSkill;

/**
 * 技能模块
 *
 * @author Lance
 */
public interface ISkillService {

    /**
     * 添加技能
     *
     * @param player 玩家对象
     * @param id     技能id
     * @param level  技能等级
     */
    void addSkill(Player player, int id, int level);

    /**
     * 删除技能
     *
     * @param player 玩家对象
     * @param id     技能id
     */
    void removeSkill(Player player, int id);

    /**
     * 查询玩家身上的技能
     *
     * @param player 玩家对象
     * @param id     技能id
     */
    AbstractSkill getSkill(Player player, int id);

    /**
     * 判断玩家身上是否有这个技能
     *
     * @param player 玩家对象
     * @param id     技能id
     */
    boolean containsSkill(Player player, int id);
}
