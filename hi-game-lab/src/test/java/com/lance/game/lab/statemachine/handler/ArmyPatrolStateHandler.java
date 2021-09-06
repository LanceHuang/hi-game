package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * 巡逻
 *
 * @author Lance
 * @since 2021/9/6
 */
public class ArmyPatrolStateHandler extends ArmyStateHandler {

    @Override
    public void entry(ArmyObject armyObject) {

    }

    @Override
    public void onState(ArmyObject armyObject) {
        System.out.println("Army patrol: " + armyObject.getName());
    }

    @Override
    public void exit(ArmyObject armyObject) {

    }
}
