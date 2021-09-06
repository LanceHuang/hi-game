package com.lance.game.lab.statemachine;

import java.util.Map;

/**
 * 状态机
 *
 * @param <S> state 状态枚举
 * @param <E> event 事件枚举
 * @author Lance
 * @since 2021/9/6
 */
public class StateMachine<S, E> {

    /** 转换规则 */
    private final Map<S, Map<E, S>> transitions;

    /** 状态 */
    private S state;

    public StateMachine(Map<S, Map<E, S>> transitions, S state) {
        this.transitions = transitions;
        this.state = state;
    }

    /**
     * 获取当前状态
     *
     * @return 状态
     */
    public S getState() {
        return state;
    }

    /**
     * 发布事件
     *
     * @param event 事件
     */
    public void publishEvent(E event) {
        Map<E, S> transitionMap = transitions.get(state);
        if (transitionMap == null) {
            return;
        }

        S newState = transitionMap.get(event);
        if (newState == null) {
            return;
        }
        state = newState;
    }
}
