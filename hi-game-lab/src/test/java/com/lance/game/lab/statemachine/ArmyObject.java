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

    private static final long DELAY = 2000L;

    /** 唯一标识 */
    private long id;

    /** 名称 */
    private String name;

    /** 状态机 */
    private StateMachine<ArmyState, ArmyStateChangeEvent> stateMachine;

    /** AI模式 */
    private boolean aiMode;

    /** 上次接管时间 */
    private long lastTime;

    /** 创建时间 */
    private long createTime = System.currentTimeMillis();

    public void tick() {
        synchronized (this) {
            if (aiMode) {
                stateMachine.getState().getArmyStateHandler().onState(this);
            } else if (lastTime + DELAY > System.currentTimeMillis()) {
                aiMode = true;
                stateMachine.getState().getArmyStateHandler().onState(this);
            }
        }
    }

    public void move() {
        manual(ArmyStateChangeEvent.MOVE);
        System.out.println("Move: " + name);
    }

    public void stopMove() {
        manual(ArmyStateChangeEvent.STOP_MOVE);
        System.out.println("StopMove: " + name);
    }

    public void manual(ArmyStateChangeEvent event) {
        synchronized (this) {
            this.aiMode = false;
            this.lastTime = System.currentTimeMillis();

            stateMachine.publishEvent(event);
        }
    }
}
