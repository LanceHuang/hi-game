package com.lance.game.module.player.model;

import com.lance.game.module.attribute.model.AttributeContainer;
import com.lance.game.module.buff.model.BuffContainer;
import com.lance.game.module.skill.model.SkillContainer;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/7/2 17:25
 */
@Data
public class Player {

    // todo 密码呢？
    private long   id;
    /** 账号 */
    private String account;
    /** 昵称 */
    private String nickname;
    /** 性别 */
    private int    gender;
    /** 等级 */
    private int    level;
    /** 经验条 */
    private long   exp;
//    /** 职业 */
//    private int    career;

    private AttributeContainer attributeContainer;
    private SkillContainer     skillContainer;
    private BuffContainer      buffContainer;

    // todo 背包 Ctrl+I 打开背包，哈哈哈（冒险岛、彩虹岛）
    // todo 装备栏

}
