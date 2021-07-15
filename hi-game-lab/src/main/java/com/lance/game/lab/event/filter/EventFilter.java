package com.lance.game.lab.event.filter;

/**
 * 事件过滤器
 *
 * @author Lance
 * @since 2021/7/15
 */
public interface EventFilter {

    /**
     * 判断事件是否满足条件
     *
     * @param eventType 事件类型
     * @return true 满足条件
     */
    boolean match(Class<?> eventType);
}
