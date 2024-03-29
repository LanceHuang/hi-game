package com.lance.game.event.invoker;

import com.lance.game.event.Event;
import com.lance.game.event.condition.EventCondition;
import com.lance.game.event.filter.EventFilter;

import java.lang.reflect.Method;

/**
 * @author Lance
 * @since 2021/7/14
 */
public abstract class AbstractEventListenerInvoker implements EventListenerInvoker {

    private Object bean;

    private Method method;

    private int order;

    private boolean async;

    private EventFilter filter;

    private EventCondition condition;

    @Override
    public boolean supportEvents(Class<?> eventType) {
        return filter.match(eventType);
    }

    @Override
    public void invokeListener(Event event) {
        if (condition != null && condition.verify(event)) {
            doInvokeListener(event);
        }
    }

    public abstract void doInvokeListener(Event event);

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public EventFilter getFilter() {
        return filter;
    }

    public void setFilter(EventFilter filter) {
        this.filter = filter;
    }

    public EventCondition getCondition() {
        return condition;
    }

    public void setCondition(EventCondition condition) {
        this.condition = condition;
    }
}
