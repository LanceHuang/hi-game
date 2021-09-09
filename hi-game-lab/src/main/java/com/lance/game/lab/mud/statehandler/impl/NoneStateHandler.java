package com.lance.game.lab.mud.statehandler.impl;

import com.lance.game.lab.mud.gameobject.GameObject;
import com.lance.game.lab.mud.statehandler.StateHandler;

/**
 * 发呆
 *
 * @author Lance
 * @since 2021/9/6
 */
public class NoneStateHandler extends StateHandler {

    @Override
    public void onEntry(GameObject gameObject) {

    }

    @Override
    public void onState(GameObject gameObject) {
        System.out.println(gameObject.getId() + "正在发呆");
    }

    @Override
    public void onExit(GameObject gameObject) {

    }
}
