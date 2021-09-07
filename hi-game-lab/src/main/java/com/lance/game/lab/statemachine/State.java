package com.lance.game.lab.statemachine;

import java.util.List;

/**
 * 状态
 *
 * @param <S> state 状态枚举
 * @param <E> event 事件枚举
 * @author Lance
 * @since 2021/9/7
 */
public class State<S, E> {

    private final S state;

    private List<Transition<S, E>> transitions;

    public State(S state) {
        this.state = state;
    }

    public S getState() {
        return state;
    }

    public List<Transition<S, E>> getTransitions() {
        return transitions;
    }


    public void addTransition(Transition<S, E> transition) {
        transitions.add(transition);
    }

    /**
     * 进入状态
     */
    public void enter() {

    }

    /**
     * 更新状态
     */
    public void update() {

    }

    /**
     * 退出状态
     */
    public void exit() {

    }

    // todo equals/hashCode
}
