package com.lance.game.event;

/**
 * 事件处理器
 *
 * @author Lance
 */
public interface EventHandler {

//    /**
//     * 判断是否支持的事件类型
//     *
//     * @param eventType 事件类型
//     */
//    boolean supportEventType(Class<?> eventType);

    /**
     * 处理事件
     */
    void onEvent(Object event);
}
