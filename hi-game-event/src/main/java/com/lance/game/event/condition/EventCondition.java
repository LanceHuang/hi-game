package com.lance.game.event.condition;

import com.lance.game.event.Event;

/**
 * 事件执行条件
 *
 * @author Lance
 * @since 2021/8/9
 */
public interface EventCondition {

    /**
     * 判断事件是否满足执行条件
     *
     * @param event 事件对象
     * @return true 满足条件
     */
    boolean verify(Event event);
}
