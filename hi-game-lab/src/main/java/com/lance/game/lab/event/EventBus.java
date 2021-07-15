package com.lance.game.lab.event;

import com.lance.game.lab.event.invoker.EventListenerInvoker;
import com.lance.game.lab.event.invoker.EventListenerInvokerComparator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件总线
 *
 * @author Lance
 * @since 2021/7/14
 */
public class EventBus {

    private final Set<EventListenerInvoker> invokers = new HashSet<>();

    private final Map<Class<?>, List<EventListenerInvoker>> invokeCache = new ConcurrentHashMap<>();

    // todo 运行时注册
    public void registerEventListenerInvoker(Object bean, Method method) {

    }

    /**
     * 注册事件监听器
     *
     * @param eventListenerInvoker 事件监听器
     */
    public void registerEventListenerInvoker(EventListenerInvoker eventListenerInvoker) {
        if (eventListenerInvoker == null) {
            return;
        }

        synchronized (this) {
            invokers.add(eventListenerInvoker);
            invokeCache.clear();
        }
    }

    /**
     * 删除事件监听器
     *
     * @param eventListenerInvoker 事件监听器
     */
    public void removeEventListenerInvoker(EventListenerInvoker eventListenerInvoker) {
        if (eventListenerInvoker == null) {
            return;
        }

        synchronized (this) {
            invokers.remove(eventListenerInvoker);
            invokeCache.clear();
        }
    }

    /**
     * 发布事件
     *
     * @param event 事件
     */
    public void publishEvent(Event event) {
        if (event == null) {
            return;
        }

        List<EventListenerInvoker> eventListenerInvokers = invokeCache.get(event.getClass());
        if (eventListenerInvokers == null) {
            eventListenerInvokers = getEventListenerInvokers(event.getClass());
        }
        if (eventListenerInvokers.isEmpty()) {
            return;
        }

        for (EventListenerInvoker eventListenerInvoker : eventListenerInvokers) {
            eventListenerInvoker.invokeListener(event);
        }
    }

    private List<EventListenerInvoker> getEventListenerInvokers(Class<?> eventClass) {
        if (!invokeCache.containsKey(eventClass)) {
            synchronized (this) {
                if (!invokeCache.containsKey(eventClass)) {
                    // 筛选符合条件的invoker
                    List<EventListenerInvoker> eventListenerInvokers = new ArrayList<>(4);
                    for (EventListenerInvoker invoker : invokers) {
                        if (invoker.supportEvents(eventClass)) {
                            eventListenerInvokers.add(invoker);
                        }
                    }

                    // 排序
                    EventListenerInvokerComparator.sort(eventListenerInvokers);

                    invokeCache.put(eventClass, eventListenerInvokers);
                }
            }
        }
        return invokeCache.get(eventClass);
    }
}
