package com.lance.game.module.buff.service;

import com.lance.game.module.buff.model.AbstractBuff;
import com.lance.game.module.player.model.Player;

/**
 * @author Lance
 */
public interface IBuffService {

    /**
     * 创建buff
     *
     * @param id buff id
     */
    AbstractBuff createBuff(int id);

    /**
     * 创建并添加buff
     *
     * @param id buff id
     */
    void createAndAddBuff(Player player, int id);

    /**
     * 移除buff
     *
     * @param id buff id
     */
    void removeBuff(Player player, int id);

    /**
     * 判断玩家身上是否有指定buff
     *
     * @param id buff id
     */
    boolean containsBuff(Player player, int id);
}
