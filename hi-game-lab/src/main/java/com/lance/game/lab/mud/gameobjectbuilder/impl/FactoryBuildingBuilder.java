package com.lance.game.lab.mud.gameobjectbuilder.impl;

import com.lance.game.lab.mud.constant.GameObjectState;
import com.lance.game.lab.mud.gameobjectbuilder.GameObjectBuilder;
import com.lance.game.lab.mud.gameobject.impl.FactoryBuilding;
import com.lance.game.lab.statemachine.StateMachineFactory;

/**
 * 工厂建筑构造器
 *
 * @author Lance
 * @since 2021/9/7
 */
public class FactoryBuildingBuilder extends GameObjectBuilder<FactoryBuilding> {

//    private StateMachineFactory<GameObjectState, > stateMachineFactory;

    public FactoryBuildingBuilder() {
        // 配置状态机
//        configurer().
    }

    @Override
    public FactoryBuilding build(int configId, long id) {
//        FactoryBuilding factoryBuilding = build();


        return null;
//        return factoryBuilding;
    }
}
