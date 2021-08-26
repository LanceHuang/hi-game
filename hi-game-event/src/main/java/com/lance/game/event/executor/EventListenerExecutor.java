package com.lance.game.event.executor;

import com.lance.game.event.Event;
import com.lance.game.event.invoker.EventListenerInvoker;

/**
 * 事件执行器
 *
 * @author Lance
 * @since 2021/7/14
 */
public interface EventListenerExecutor {

    /**
     * 调用监听器
     *
     * @param invoker 监听器
     * @param event   事件
     */
    void invokeListener(EventListenerInvoker invoker, Event event);
}
