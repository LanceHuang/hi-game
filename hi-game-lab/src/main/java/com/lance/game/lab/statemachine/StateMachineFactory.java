package com.lance.game.lab.statemachine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态机工厂
 *
 * @param <S> state 状态枚举
 * @param <E> event 事件枚举
 * @author Lance
 * @since 2021/9/6
 */
public class StateMachineFactory<S, E> {

    /** 转换规则 */
    private final Map<S, Map<E, S>> transitions = new HashMap<>();

    /** 初始状态 */
    private S initialState;

    /**
     * 设置初始状态
     *
     * @param initialState 初始状态
     */
    public StateMachineFactory<S, E> initState(S initialState) {
        this.initialState = initialState;
        return this;
    }

    /**
     * 添加转换规则
     *
     * @param source 原状态
     * @param target 目标状态
     * @param event  触发事件
     */
    public StateMachineFactory<S, E> trans(S source, S target, E event) {
        Map<E, S> transitionMap = transitions.compute(source, (k, v) -> {
            if (v == null) {
                v = new HashMap<>();
            }
            return v;
        });

        transitionMap.putIfAbsent(event, target);

        return this;
    }

    /**
     * 创建状态机
     *
     * @return 状态机
     */
    public StateMachine<S, E> create() {
        return new StateMachine<>(Collections.unmodifiableMap(transitions), initialState);
    }
}
