package com.lance.game.demo.module.world.config;

import lombok.Data;

/**
 * 地图配置
 *
 * @author Lance
 */
@Data
public class MapConfig {

    /** 地图id */
    private int mapId;
    /** 初始频道数（新手村） */
    private int initChannelNum;
    /** 最大频道数 */
    private int maxChannelNum;
    /** 最大玩家数（单人场景） */
    private int maxPlayerNum;
    /** 增线玩家数（一般都会小于maxPlayerNum，这样能让玩家体验更流畅） */
    private int addPlayerNum;

}
