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

    /** 初始状态 */
    private final S initialState;

    /** 状态 */
    private S state;

    /** 上下文 */
    private StateMachineContext context;

    public StateMachine(Map<S, Map<E, S>> transitions, S initialState) {
        this.transitions = transitions;
        this.initialState = initialState;
        this.state = initialState;
        this.context = new StateMachineContext();
    }

    /**
     * 发送事件
     *
     * @param event 事件
     */
    public void sendEvent(E event) {
        if (isComplete()) {
            // todo log complete
            return;
        }

        Map<E, S> transitionMap = transitions.get(state);
        if (transitionMap == null) {
            return;
        }

        S newState = transitionMap.get(event);
        if (newState == null) {
            return;
        }

        if (state != newState) {
            S oldState = state;
            state = newState;
            notifyChange(oldState, newState);
        }
    }

    private void notifyChange(S source, S target) {
//        source.exit(context);
//        target.enter(context);
    }

    /**
     * 判断是否已达终态。若没有下一个转换状态，则视为已达终态
     *
     * @return true 已达终态
     */
    public boolean isComplete() {
        return !transitions.containsKey(state);
    }

    /**
     * 重置为初始状态
     */
    public void restore() {
        state = initialState;
    }

    public Map<S, Map<E, S>> getTransitions() {
        return transitions;
    }

    public S getInitialState() {
        return initialState;
    }

    public S getState() {
        return state;
    }

    public StateMachineContext getContext() {
        return context;
    }
}
