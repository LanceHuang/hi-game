package com.lance.game.lab.statemachine;

import com.lance.game.lab.mud.constant.GameObjectStateChangeEvent;
import com.lance.game.lab.mud.constant.GameObjectState;

/**
 * AI构造器
 *
 * @author Lance
 * @since 2021/9/6
 */
public class ArmyCreator {

    private StateMachineFactory<GameObjectState, GameObjectStateChangeEvent> stateMachineFactory;

    public ArmyCreator(StateMachineFactory<GameObjectState, GameObjectStateChangeEvent> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    public ArmyObject createArmyObject(String name) {
        ArmyObject armyObject = new ArmyObject();
        armyObject.setId(System.currentTimeMillis());
        armyObject.setName(name);
        armyObject.setStateMachine(stateMachineFactory.create());

//        StateMachine<GameObjectState, GameObjectStateChangeEvent> stateMachine = stateMachineFactory.create();
//        stateMachine.setAttribute("owner", armyObject);
//        armyObject.setStateMachine(stateMachine);
//
        return armyObject;
    }
}
