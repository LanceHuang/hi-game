package com.lance.game.mud.config;

import com.lance.game.mud.gameobject.GameObjectType;

/**
 * 游戏单位配置
 *
 * @author Lance
 * @since 2021/9/7
 */
public class GameObjectConfig {

    private int id;

    private String name;

    private GameObjectType objectType;

    public static GameObjectConfig valueOf(int id, String name, GameObjectType objectType) {
        GameObjectConfig gameObjectConfig = new GameObjectConfig();
        gameObjectConfig.id = id;
        gameObjectConfig.name = name;
        gameObjectConfig.objectType = objectType;
        return gameObjectConfig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(GameObjectType objectType) {
        this.objectType = objectType;
    }
}
