package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * 发呆
 *
 * @author Lance
 * @since 2021/9/6
 */
public class NoneStateHandler extends StateHandler {

    @Override
    public void onEntry(ArmyObject armyObject) {

    }

    @Override
    public void onState(ArmyObject armyObject) {
        System.out.println(armyObject.getName() + "正在发呆");
    }

    @Override
    public void onExit(ArmyObject armyObject) {

    }
}
