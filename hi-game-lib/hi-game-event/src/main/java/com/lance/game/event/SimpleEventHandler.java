package com.lance.game.event;

import java.lang.reflect.Method;

/**
 * @author Lance
 */
public class SimpleEventHandler implements EventHandler {

    private Object bean;

    private Method m;

    private Class<?> eventType;

    public SimpleEventHandler(Object bean, Method m, Class<?> eventType) {
        this.bean = bean;
        this.m = m;
        this.eventType = eventType;
    }

    @Override
    public boolean supportEventType(Class<?> eventType) {
        return this.eventType == eventType;
    }

    @Override
    public void onEvent(Object event) {
        try {
            m.invoke(bean, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
