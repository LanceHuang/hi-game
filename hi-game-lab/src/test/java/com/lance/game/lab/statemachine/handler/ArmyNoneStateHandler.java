package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * @author Lance
 * @since 2021/9/6
 */
public class ArmyNoneStateHandler extends ArmyStateHandler {

    @Override
    public void entry(ArmyObject armyObject) {

    }

    @Override
    public void onState(ArmyObject armyObject) {
        System.out.println("Army stand by: " + armyObject.getName());
        armyObject.move();
    }

    @Override
    public void exit(ArmyObject armyObject) {

    }
}
