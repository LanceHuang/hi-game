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
    void enterMap(Player player, int mapId); // todo 现在是自动选频道，如果想实现冒险岛那种选频，则需要修改一下接口

    // todo 还要解决切换场景的问题
}
