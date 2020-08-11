package com.lance.game.demo.module.world.service;

import com.lance.game.demo.module.player.model.Player;

/**
 * 地图相关业务
 *
 * @author Lance
 */
public interface IWorldService {

    /**
     * 初始化
     */
    void init();

    /**
     * 玩家进入地图
     *
     * @param mapId 地图id
     */
    void enterMap(Player player, int mapId);

    // todo 看一下要不要搞个离开地图，若场景中没有玩家，则回收
}
