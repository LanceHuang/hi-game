package com.lance.game.demo.module.world.model;

import lombok.Data;

/**
 * 场景
 *
 * @author Lance
 */
@Data
public class Scene {

    /** 地图id */
    private int mapId;
    /** 频道id */
    private int channelId;
    /** 玩家数 */
    private int playerCount;

    public Scene(int mapId, int channelId) {
        this.mapId = mapId;
        this.channelId = channelId;
    }

    /**
     * 增加场景内玩家数
     */
    public void addPlayer() {
        this.playerCount++;
    }
}
