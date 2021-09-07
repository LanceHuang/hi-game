package com.lance.game.lab.statemachine.listener;

/**
 * 状态机监听器
 *
 * @author Lance
 * @since 2021/9/7
 */
public interface StateMachineListener<S> {

    /**
     * 进入状态
     *
     * @param state 状态
     */
    void onEnter(S state);

//    /**
//     * 进入状态
//     *
//     * @param state 状态
//     */
//    void onUpdate(S state);

    /**
     * 退出状态
     *
     * @param state 状态
     */
    void onExit(S state);
}
