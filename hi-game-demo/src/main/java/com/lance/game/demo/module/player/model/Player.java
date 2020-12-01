package com.lance.game.demo.module.player.model;

import com.lance.game.demo.module.attribute.AttributeContainer;
import com.lance.game.demo.module.buff.model.BuffContainer;
import com.lance.game.demo.module.skill.model.SkillContainer;
import com.lance.game.demo.module.storage.model.ItemStorage;
import com.lance.game.demo.module.world.model.WorldPosition;
import com.lance.game.mongodb.annotation.MongoIgnore;
import lombok.Data;

/**
 * 玩家对象
 *
 * @author Lance
 */
@Data
public class Player {

    /** 唯一标识 */
    private long id;
    /** 账号 */
    private String account;
    /** 昵称 */
    private String nickname;
    /** 性别 */
    private int gender;
    /** 等级 */
    private int level;
    /** 经验条 */
    private long exp;

    @MongoIgnore
    private AttributeContainer attributeContainer;
    @MongoIgnore
    private SkillContainer skillContainer;
    @MongoIgnore
    private BuffContainer buffContainer;

    // todo 背包 Ctrl+I 打开背包，哈哈哈（冒险岛、彩虹岛）
    // 1. 客户端设计快捷键？并且支持保存配置
    // 2. 支持整理？背包格子有index（数据库，index -> 道具）
    // todo 装备栏

    // todo 暂时不考虑多种背包的情况，还有仓库的情况
    @MongoIgnore
    private ItemStorage itemStorage;

    /** 玩家位置 */
    private WorldPosition worldPosition;
}
