package com.lance.game.lab.event.filter;

import com.lance.game.lab.event.annotation.EventListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Lance
 * @since 2021/7/15
 */
public class AnnotationEventFilter implements EventFilter {

    private final Class<?>[] resolveTypes;

    public AnnotationEventFilter(Object bean, Method method) {
        EventListener eventListener = method.getAnnotation(EventListener.class);
        Class<?>[] eventListenerValue = eventListener.value();
        if (eventListenerValue.length == 0) {
            throw new IllegalArgumentException("EventListener.value cannot be empty: " + bean.getClass().getName() + "#" + method.getName());
        } else {
            for (Class<?> resolveType : eventListenerValue) {
                if (!resolveType.isAnnotation()) {
                    throw new IllegalArgumentException("Unexpected EventListener.value: " + bean.getClass().getName() + "#" + method.getName()
                            + " " + resolveType.getName());
                }
            }
            this.resolveTypes = eventListenerValue;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean match(Class<?> eventType) {
        for (Class<?> resolveType : resolveTypes) {
            if (eventType.isAnnotationPresent((Class<? extends Annotation>) resolveType)) {
                return true;
            }
        }
        return false;
    }
}
