package com.lance.game.lab.statemachine.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合状态机监听器
 *
 * @author Lance
 * @since 2021/9/7
 */
public class CompositeStateMachineListener<S> implements StateMachineListener<S> {

    private final List<StateMachineListener<S>> listeners = new ArrayList<>(0);

    @Override
    public void onEnter(S state) {
        listeners.forEach(listener -> listener.onEnter(state));
    }

    @Override
    public void onExit(S state) {
        listeners.forEach(listener -> listener.onExit(state));
    }

    /**
     * 添加监听器
     *
     * @param listener 监听器
     */
    public void addListener(StateMachineListener<S> listener) {
        if (listener == null) {
            return;
        }
        listeners.add(listener);
    }
}
