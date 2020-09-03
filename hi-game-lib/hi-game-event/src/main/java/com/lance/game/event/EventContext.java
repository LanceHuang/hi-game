package com.lance.game.event;

import java.util.HashSet;
import java.util.Set;

/**
 * 事件上下文
 *
 * @author Lance
 */
public class EventContext {

    /** 事件处理器 */
    private Set<EventHandler> eventHandlers;

    public EventContext() {
        this.eventHandlers = new HashSet<>();
    }

    /**
     * 添加事件处理器
     */
    public void addEventHandler(EventHandler eventHandler) {
        this.eventHandlers.add(eventHandler);
    }

    // 整个生命周期里好像都不会删除
//    /**
//     * 删除事件处理器
//     */
//    public void removeEventHandler(EventHandler eventHandler) {
//        this.eventHandlers.remove(eventHandler);
//    }
//
//    /**
//     * 删除所有事件处理器
//     */
//    public void removeAllEventHandlers() {
//        this.eventHandlers.clear();
//    }

    /**
     * 获取事件处理器
     *
     * @param eventType 事件类型
     */
    public Set<EventHandler> getEventHandler(Class<?> eventType) {
        Set<EventHandler> result = new HashSet<>();
        for (EventHandler eventHandler : this.eventHandlers) {
            if (eventHandler.getEventType() == eventType) {
                result.add(eventHandler);
            }
        }
        return result;
    }
}
