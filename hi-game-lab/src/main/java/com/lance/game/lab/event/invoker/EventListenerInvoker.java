package com.lance.game.lab.event.invoker;

import com.lance.game.lab.event.Event;

/**
 * 事件监听器
 *
 * @author Lance
 * @since 2021/7/14
 */
public interface EventListenerInvoker {

    /**
     * 校验是否支持事件类型
     *
     * @param eventType 事件类型
     * @return true 支持的类型
     */
    boolean supportEvents(Class<?> eventType);

    /**
     * 调用监听器
     *
     * @param event 事件
     */
    void invokeListener(Event event);
}
