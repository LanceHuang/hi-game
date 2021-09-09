package com.lance.game.lab.mud.statehandler.impl;

import com.lance.game.lab.mud.gameobject.GameObject;
import com.lance.game.lab.mud.statehandler.StateHandler;

/**
 * 采集
 *
 * @author Lance
 * @since 2021/9/7
 */
public class GatherStateHandler extends StateHandler {

    @Override
    public void onEntry(GameObject gameObject) {

    }

    @Override
    public void onState(GameObject gameObject) {
//        armyObject.doGather();
//        System.out.println(armyObject.getName() + "正在采集，进度为：" + armyObject.getGatherProgress());
    }

    @Override
    public void onExit(GameObject gameObject) {

    }
}
