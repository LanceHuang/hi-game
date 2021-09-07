package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * 回城
 *
 * @author Lance
 * @since 2021/9/6
 */
public class BackToTheCityStateHandler extends StateHandler {

    @Override
    public void onEntry(ArmyObject armyObject) {

    }

    @Override
    public void onState(ArmyObject armyObject) {
        System.out.println(armyObject.getName() + "正在回城，并清空采集物");
        armyObject.clearGather();
    }

    @Override
    public void onExit(ArmyObject armyObject) {

    }
}
