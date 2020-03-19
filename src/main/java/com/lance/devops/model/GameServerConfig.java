package com.lance.devops.model;

/**
 * 游戏服务器配置
 *
 * @author Lance
 * @since 2019/10/29 14:48
 */
public class GameServerConfig {

    private int    serverId;
    private String ip;
    private int    port;

    private GameDatabaseConfig databaseConfig;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public GameDatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public void setDatabaseConfig(GameDatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }
}
