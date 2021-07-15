package com.lance.game.lab.event.filter;

import com.lance.game.lab.event.annotation.EventListener;

import java.lang.reflect.Method;

/**
 * @author Lance
 * @since 2021/7/15
 */
public class AssignableEventFilter implements EventFilter {

    private final Class<?>[] resolveTypes;

    public AssignableEventFilter(Object bean, Method method) {
        // 方法参数类型
        Class<?> parameterClass = method.getParameters()[0].getType();

        // 配置类型
        EventListener eventListener = method.getAnnotation(EventListener.class);
        Class<?>[] eventListenerValue = eventListener.value();
        if (eventListenerValue.length == 0) {
            this.resolveTypes = new Class[]{parameterClass};
        } else {
            for (Class<?> eventClass : eventListenerValue) {
                if (!parameterClass.isAssignableFrom(eventClass)) {
                    throw new IllegalArgumentException("Illegal event type: " + bean.getClass().getName() + "#" + method.getName()
                            + " " + eventClass.getName());
                }
            }
            this.resolveTypes = eventListenerValue;
        }
    }

    @Override
    public boolean match(Class<?> eventType) {
        for (Class<?> resolveType : resolveTypes) {
            if (resolveType == eventType) {
                return true;
            }
        }
        return false;
    }
}
