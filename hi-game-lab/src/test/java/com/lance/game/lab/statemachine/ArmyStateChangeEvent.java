package com.lance.game.lab.statemachine;

/**
 * 军队状态改变事件
 *
 * @author Lance
 * @since 2021/9/6
 */
public enum ArmyStateChangeEvent {

    /** 移动 */
    MOVE,
    /** 停止移动 */
    STOP_MOVE;
}
