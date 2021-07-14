package com.lance.game.lab.event.invoker;

import com.lance.game.lab.event.Event;
import com.lance.game.lab.event.executor.EventListenerExecutor;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class EventListenerInvokerProxy implements EventListenerInvoker {

    private EventListenerExecutor executor;

    private EventListenerInvoker invoker;

    public EventListenerInvokerProxy(EventListenerExecutor executor, EventListenerInvoker invoker) {
        this.executor = executor;
        this.invoker = invoker;
    }

    @Override
    public boolean supportEvents(Class<?> eventClass) {
        return invoker.supportEvents(eventClass);
    }

    @Override
    public void invokeListener(Event event) {
        executor.invokeListener(invoker, event);
    }

    public EventListenerExecutor getExecutor() {
        return executor;
    }

    public EventListenerInvoker getInvoker() {
        return invoker;
    }
}
