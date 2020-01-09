package com.lance.devops.model;

import lombok.Data;

/**
 * 游戏服务器配置
 *
 * @author Lance
 * @since 2019/10/29 14:48
 */
@Data
public class GameServerConfig {

    private int    serverId;
    private String ip;
    private int    port;

    private GameDatabaseConfig databaseConfig;
}
