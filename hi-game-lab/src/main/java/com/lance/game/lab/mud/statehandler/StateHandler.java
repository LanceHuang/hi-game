package com.lance.game.lab.mud.statehandler;

import com.lance.game.lab.mud.gameobject.GameObject;

/**
 * 游戏单位状态处理器
 *
 * @author Lance
 * @since 2021/9/6
 */
public abstract class StateHandler {

    /**
     * 进入状态
     *
     * @param gameObject 游戏单位
     */
    public abstract void onEntry(GameObject gameObject);

    /**
     * 当前状态
     *
     * @param gameObject 游戏单位
     */
    public abstract void onState(GameObject gameObject);

    /**
     * 退出状态
     *
     * @param gameObject 游戏单位
     */
    public abstract void onExit(GameObject gameObject);
}
