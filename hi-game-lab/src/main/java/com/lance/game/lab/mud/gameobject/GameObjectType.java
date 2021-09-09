package com.lance.game.lab.mud.gameobject;

import com.lance.game.lab.mud.gameobjectbuilder.GameObjectBuilder;
import com.lance.game.lab.mud.gameobjectbuilder.impl.FactoryBuildingBuilder;
import com.lance.game.lab.mud.gameobjectbuilder.impl.HeroBuilder;
import com.lance.game.lab.mud.gameobjectbuilder.impl.TreeBuilder;

/**
 * 游戏单位类型
 *
 * @author Lance
 * @since 2021/9/7
 */
public enum GameObjectType {

    /** 工厂建筑 */
    FACTORY_BUILDING(new FactoryBuildingBuilder()),
    /** 英雄 */
    HERO(new HeroBuilder()),
    /** 树木 */
    TREE(new TreeBuilder()),
    ;

    private final GameObjectBuilder gameObjectBuilder;

    GameObjectType(GameObjectBuilder gameObjectBuilder) {
        this.gameObjectBuilder = gameObjectBuilder;
    }

    public GameObjectBuilder getGameObjectBuilder() {
        return gameObjectBuilder;
    }
}
