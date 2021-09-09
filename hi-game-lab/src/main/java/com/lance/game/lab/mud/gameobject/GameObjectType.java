package com.lance.game.lab.mud.gameobject;

import com.lance.game.lab.mud.gameobjectbuilder.GameObjectBuilder;
import com.lance.game.lab.mud.gameobjectbuilder.impl.FarmerBuilder;
import com.lance.game.lab.mud.gameobjectbuilder.impl.MainCityBuilder;
import com.lance.game.lab.mud.gameobjectbuilder.impl.TreeBuilder;

/**
 * 游戏单位类型
 *
 * @author Lance
 * @since 2021/9/7
 */
public enum GameObjectType {

    /** 树木 */
    TREE(new TreeBuilder()),
    /** 主城 */
    MAIN_CITY(new MainCityBuilder()),
    /** 农民 */
    FARMER(new FarmerBuilder()),

    ;

    private final GameObjectBuilder gameObjectBuilder;

    GameObjectType(GameObjectBuilder gameObjectBuilder) {
        this.gameObjectBuilder = gameObjectBuilder;
    }

    public GameObjectBuilder getGameObjectBuilder() {
        return gameObjectBuilder;
    }
}
