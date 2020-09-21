package com.lance.game.event;

/**
 * 事件多播器（既可以同步，又可以异步，还可以队列）
 *
 * @author Lance
 */
public interface EventMulticaster {

    /**
     * 事件多播
     */
    void multicastEvent(Object event);
}
