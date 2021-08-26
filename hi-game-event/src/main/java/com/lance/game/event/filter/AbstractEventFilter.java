package com.lance.game.event.filter;

import java.lang.reflect.Method;

/**
 * @author Lance
 * @since 2021/7/15
 */
public abstract class AbstractEventFilter implements EventFilter {

    public AbstractEventFilter(Object bean, Method method) {
    }
}
