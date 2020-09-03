package com.lance.game.event;

/**
 * 事件处理器
 *
 * @author Lance
 */
public interface EventHandler {

    /**
     * 获取处理的事件类型
     */
    Class<?> getEventType();

    /**
     * 处理事件
     */
    void onEvent(Object event);
}
