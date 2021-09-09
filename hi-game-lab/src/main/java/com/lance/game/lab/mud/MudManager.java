package com.lance.game.lab.mud;

import com.lance.game.lab.mud.config.GameObjectConfig;
import com.lance.game.lab.mud.gameobject.GameObjectType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/9/7
 */
public class MudManager {

    private final Map<Integer, GameObjectConfig> gameObjectConfigMap = new HashMap<>();

    @PostConstruct
    public void init() {
        addGameObjectConfig(GameObjectConfig.valueOf(1, "主城", GameObjectType.MAIN_CITY));
        addGameObjectConfig(GameObjectConfig.valueOf(2, "树木", GameObjectType.TREE));
        addGameObjectConfig(GameObjectConfig.valueOf(3, "农民", GameObjectType.FARMER));
    }

    public void addGameObjectConfig(GameObjectConfig gameObjectConfig) {
        gameObjectConfigMap.put(gameObjectConfig.getId(), gameObjectConfig);
    }

    public GameObjectConfig getGameObjectConfig(int id) {
        return gameObjectConfigMap.get(id);
    }

}
