package com.lance.game.lab.mud.gameobject;

import com.lance.game.lab.mud.gameobject.builder.GameObjectBuilder;
import com.lance.game.lab.mud.gameobject.builder.impl.FactoryBuildingBuilder;
import com.lance.game.lab.mud.gameobject.builder.impl.HeroBuilder;
import com.lance.game.lab.mud.gameobject.builder.impl.TreeBuilder;

/**
 * 游戏对象类型
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
