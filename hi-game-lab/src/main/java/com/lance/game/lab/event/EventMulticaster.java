package com.lance.game.lab.event;

/**
 * 事件广播器
 *
 * @author Lance
 */
public interface EventMulticaster {

    /**
     * 广播事件
     */
    void multicastEvent(Object event);
}
