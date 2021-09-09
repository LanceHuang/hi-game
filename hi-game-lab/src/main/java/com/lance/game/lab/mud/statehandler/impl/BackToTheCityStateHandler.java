package com.lance.game.lab.mud.statehandler.impl;

import com.lance.game.lab.mud.gameobject.GameObject;
import com.lance.game.lab.mud.statehandler.StateHandler;

/**
 * 回城
 *
 * @author Lance
 * @since 2021/9/6
 */
public class BackToTheCityStateHandler extends StateHandler {

    @Override
    public void onEntry(GameObject gameObject) {

    }

    @Override
    public void onState(GameObject gameObject) {
//        System.out.println(armyObject.getName() + "正在回城，并清空采集物");
//        armyObject.clearGather();
    }

    @Override
    public void onExit(GameObject gameObject) {

    }
}
