package com.lance.game.lab.statemachine;

/**
 * AI构造器
 *
 * @author Lance
 * @since 2021/9/6
 */
public class ArmyCreator {

    private StateMachineFactory<ArmyState, ArmyStateChangeEvent> stateMachineFactory;

    public ArmyCreator(StateMachineFactory<ArmyState, ArmyStateChangeEvent> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    public ArmyObject createArmyObject(String name) {
        ArmyObject armyObject = new ArmyObject();
        armyObject.setId(System.currentTimeMillis());
        armyObject.setName(name);
        armyObject.setStateMachine(stateMachineFactory.create());
        return armyObject;
    }
}
