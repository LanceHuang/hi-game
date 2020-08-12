package com.lance.game.demo.module.world.model;

import lombok.Data;

/**
 * 玩家位置
 *
 * @author Lance
 */
@Data
public class WorldPosition {

    /** 地图id */
    private int mapId;

    /** 频道id */
    private int channelId;

    /** 坐标x */
    private int x;

    /** 坐标y */
    private int y;
}
