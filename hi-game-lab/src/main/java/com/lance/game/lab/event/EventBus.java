package com.lance.game.lab.event;

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

    private final Map<Class<?>, List<EventListenerInvoker>> invokeCache = new ConcurrentHashMap<>(64);

    public void addListenerInvoker(EventListenerInvoker eventListenerInvoker) {
        synchronized (this) {
            invokers.add(eventListenerInvoker);
            invokeCache.clear();
        }
    }

    public void removeListenerInvoker(EventListenerInvoker eventListenerInvoker) {
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
    public void submit(Event event) {
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
