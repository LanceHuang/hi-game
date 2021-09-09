package com.lance.game.lab.mud.statehandler.impl;

import com.lance.game.lab.mud.gameobject.GameObject;
import com.lance.game.lab.mud.statehandler.StateHandler;

/**
 * 巡逻
 *
 * @author Lance
 * @since 2021/9/6
 */
public class PatrolStateHandler extends StateHandler {

    @Override
    public void onEntry(GameObject gameObject) {

    }

    @Override
    public void onState(GameObject gameObject) {
        System.out.println(gameObject.getId() + "正在巡逻");
    }

    @Override
    public void onExit(GameObject gameObject) {

    }
}
