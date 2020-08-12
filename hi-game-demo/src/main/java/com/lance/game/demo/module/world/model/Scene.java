package com.lance.game.demo.module.world.model;

import com.lance.game.demo.module.player.model.Player;
import lombok.Data;

import java.util.Set;

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

    /** 场景内玩家id */
    private Set<Long> playerIds;

    public Scene(int mapId, int channelId) {
        this.mapId = mapId;
        this.channelId = channelId;
    }

    /**
     * 玩家进入场景
     */
    public void addPlayer(Player player) {
        this.playerIds.add(player.getId());
    }

    /**
     * 玩家离开场景
     */
    public void removePlayer(Player player) {
        this.playerIds.remove(player.getId());
    }

    /**
     * 计算场景内玩家数
     */
    public int countPlayer() {
        return this.playerIds.size();
    }

}
