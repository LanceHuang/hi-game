package com.lance.game.lab.event;

/**
 * 事件发布者（可发布跨服事件）
 *
 * @author Lance
 */
public interface EventPublisher {

    /**
     * 发布事件
     */
    void publishEvent(Object event);
}
