package com.lance.game.lab.mud.manager;

import com.lance.game.lab.mud.config.GameObjectConfig;
import com.lance.game.lab.mud.gameobject.GameObject;

import java.util.Map;

/**
 * @author Lance
 * @since 2021/9/7
 */
public class MudManager {

    private Map<Integer, GameObjectConfig> gameObjectConfigMap;

    public void init() {
        // todo
    }

    public GameObjectConfig getGameObjectConfig(int id) {
        return gameObjectConfigMap.get(id);
    }

}
