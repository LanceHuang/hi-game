package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * 采集
 *
 * @author Lance
 * @since 2021/9/7
 */
public class GatherStateHandler extends StateHandler {

    @Override
    public void onEntry(ArmyObject armyObject) {

    }

    @Override
    public void onState(ArmyObject armyObject) {
        armyObject.doGather();
        System.out.println(armyObject.getName() + "正在采集，进度为：" + armyObject.getGatherProgress());
    }

    @Override
    public void onExit(ArmyObject armyObject) {

    }
}
