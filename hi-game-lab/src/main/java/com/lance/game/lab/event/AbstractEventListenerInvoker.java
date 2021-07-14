package com.lance.game.lab.event;

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

    public AbstractEventListenerInvoker(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

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
}
