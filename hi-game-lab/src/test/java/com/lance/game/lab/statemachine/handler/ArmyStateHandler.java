package com.lance.game.lab.statemachine.handler;

import com.lance.game.lab.statemachine.ArmyObject;

/**
 * 军队状态处理器
 *
 * @author Lance
 * @since 2021/9/6
 */
public abstract class ArmyStateHandler {

    /**
     * 进入状态
     *
     * @param armyObject 军队
     */
    public abstract void entry(ArmyObject armyObject);

    /**
     * 当前状态时
     *
     * @param armyObject 军队
     */
    public abstract void onState(ArmyObject armyObject);

    /**
     * 退出状态
     *
     * @param armyObject 军队
     */
    public abstract void exit(ArmyObject armyObject);
}
