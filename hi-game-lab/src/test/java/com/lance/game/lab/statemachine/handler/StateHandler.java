package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * 军队状态处理器
 *
 * @author Lance
 * @since 2021/9/6
 */
public abstract class StateHandler {

    /**
     * 进入状态
     *
     * @param armyObject 军队
     */
    public abstract void onEntry(ArmyObject armyObject);

    /**
     * 当前状态
     *
     * @param armyObject 军队
     */
    public abstract void onState(ArmyObject armyObject);

    /**
     * 退出状态
     *
     * @param armyObject 军队
     */
    public abstract void onExit(ArmyObject armyObject);
}
