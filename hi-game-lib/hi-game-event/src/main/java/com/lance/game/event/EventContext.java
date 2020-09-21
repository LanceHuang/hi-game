package com.lance.game.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 事件上下文
 *
 * @author Lance
 */
public class EventContext {

    private static final List<EventHandler> EMPTY_EVENT_HANDLERS = Collections.emptyList();

    /** 事件处理器注册表：事件类型 -> 事件处理器 */
    private final Map<Class<?>, List<EventHandler>> eventHandlerMap = new HashMap<>();

    /**
     * 注册事件处理器
     *
     * @param eventType    事件类型
     * @param eventHandler 事件处理器
     */
    public void registerEventHandler(Class<?> eventType, EventHandler eventHandler) {
        this.eventHandlerMap.compute(eventType, (k, v) -> {
            if (v == null) {
                v = new LinkedList<>();
            }
            v.add(eventHandler);
            return v;
        });
    }

    /**
     * 获取事件处理器
     *
     * @param eventType 事件类型
     */
    public List<EventHandler> getEventHandler(Class<?> eventType) {
        return this.eventHandlerMap.getOrDefault(eventType, EMPTY_EVENT_HANDLERS);
    }
}
