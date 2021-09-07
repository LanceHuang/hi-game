package com.lance.game.lab.statemachine;

/**
 * 军队状态改变事件
 *
 * @author Lance
 * @since 2021/9/6
 */
public enum ArmyStateChangeEvent {

    /** 巡逻 */
    PATROL,
    /** 停止巡逻 */
    STOP_PATROL,
    /** 采集 */
    GATHER,
    /** 停止采集 */
    STOP_GATHER,
    /** 完成采集 */
    COMPLETE_GATHER,
    /** 清空采集物 */
    CLEAR_GATHER,
    ;
}
