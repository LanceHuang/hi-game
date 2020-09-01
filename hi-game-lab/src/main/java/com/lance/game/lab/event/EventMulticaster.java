package com.lance.game.lab.event;

/**
 * 事件广播器（既可以同步，又可以异步，还可以队列）
 *
 * @author Lance
 */
public interface EventMulticaster {

    /**
     * 广播事件
     */
    void multicastEvent(Object event);
}
