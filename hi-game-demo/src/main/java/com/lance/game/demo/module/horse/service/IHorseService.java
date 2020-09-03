package com.lance.game.demo.module.horse.service;

import com.lance.game.demo.module.player.model.Player;

/**
 * 坐骑（演示模块）
 *
 * @author Lance
 */
public interface IHorseService {

    /**
     * 上坐骑
     *
     * @param horseId 坐骑id
     */
    void ride(Player player, int horseId);

    /**
     * 处理上坐骑事件
     *
     * @param horseId 坐骑id
     */
    void onRideHorseEvent(Player player, int horseId);
}
