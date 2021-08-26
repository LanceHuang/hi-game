package com.lance.game.event;

import com.lance.game.event.filter.EventFilter;

/**
 * @author Lance
 * @since 2021/7/15
 */
public class MyCustomEventFilter implements EventFilter {

    @Override
    public boolean match(Class<?> eventType) {
        // todo 过滤逻辑
        return true;
    }
}