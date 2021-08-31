package com.lance.game.demo.module.skill.service;

import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.skill.model.Skill;

/**
 * 技能模块
 *
 * @author Lance
 * @since 2021/8/31
 */
public interface ISkillService {

    /**
     * 学习技能
     *
     * @param player 玩家对象
     * @param id     技能id
     * @param level  技能等级
     */
    void learnSkill(Player player, int id, int level);

    /**
     * 遗忘技能
     *
     * @param player 玩家对象
     * @param id     技能id
     */
    void forgetSkill(Player player, int id);

    /**
     * 使用技能
     *
     * @param player 玩家对象
     * @param id     技能id
     */
    void useSkill(Player player, int id);

    /**
     * 查询玩家身上的技能
     *
     * @param player 玩家对象
     * @param id     技能id
     * @return 技能实体
     */
    Skill getSkill(Player player, int id);

    /**
     * 判断玩家身上是否有这个技能
     *
     * @param player 玩家对象
     * @param id     技能id
     * @return true 有相应的技能
     */
    boolean containsSkill(Player player, int id);
}
