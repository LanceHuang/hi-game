package com.lance.game.lab.statemachine;

import lombok.Getter;
import lombok.Setter;

/**
 * 军队
 *
 * @author Lance
 * @since 2021/9/6
 */
@Getter
@Setter
public class ArmyObject {

    private static final long DELAY = 5000L;

    private static final int MAX_GATHER_PROGRESS = 5;

    /** 唯一标识 */
    private long id;

    /** 名称 */
    private String name;

    /** 状态机 */
    private StateMachine<ArmyState, ArmyStateChangeEvent> stateMachine;

    /** 采集进度 */
    private int gatherProgress;

    /** AI模式 */
    private boolean aiMode = true;

    /** 上次接管时间 */
    private long lastTime;

    /** 创建时间 */
    private long createTime = System.currentTimeMillis();

    public void tick() {
        synchronized (this) {
            if (aiMode) {
                stateMachine.getState().getArmyStateHandler().onState(this);
            } else if (System.currentTimeMillis() > lastTime + DELAY) {
                System.out.printf("超过%dms未接管，%s进入AI模式\n", DELAY, getName());
                aiMode = true;
                stateMachine.getState().getArmyStateHandler().onState(this);
            }
        }
    }

    //============================================ 玩家指令 =======================================

    public void patrol() {
        sendInstruction(ArmyStateChangeEvent.PATROL);
    }

    public void stopPatrol() {
        sendInstruction(ArmyStateChangeEvent.STOP_PATROL);
    }

    public void gather() {
        sendInstruction(ArmyStateChangeEvent.GATHER);
    }

    public void stopGather() {
        sendInstruction(ArmyStateChangeEvent.STOP_GATHER);
    }

    /**
     * 发送指令
     *
     * @param event 事件
     */
    public void sendInstruction(ArmyStateChangeEvent event) {
        synchronized (this) {
            this.aiMode = false;
            this.lastTime = System.currentTimeMillis();

            sendEvent(event);
        }
    }

    //============================================ 对象行为 =======================================

    /**
     * 采集
     */
    public void doGather() {
        if (gatherProgress < MAX_GATHER_PROGRESS) {
            gatherProgress++;
        }
        if (gatherProgress >= MAX_GATHER_PROGRESS) {
            sendEvent(ArmyStateChangeEvent.COMPLETE_GATHER);
        }
    }

    /**
     * 清空采集物
     */
    public void clearGather() {
        gatherProgress = 0;
        sendEvent(ArmyStateChangeEvent.CLEAR_GATHER);
    }

    public void sendEvent(ArmyStateChangeEvent event) {
        synchronized (this) {
            ArmyState oldState = stateMachine.getState();
            stateMachine.sendEvent(event);
            ArmyState newState = stateMachine.getState();
            if (newState != oldState) {
                System.out.printf("接收到%s事件，%s由%s状态改为%s状态\n",
                        event.name(), getName(), oldState.name(), newState.name());
            }
        }
    }
}
