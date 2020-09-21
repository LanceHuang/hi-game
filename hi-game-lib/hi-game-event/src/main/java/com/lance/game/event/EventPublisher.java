package com.lance.game.event;

/**
 * 事件发布者（预留出来，以后可能要发布跨服事件）
 *
 * @author Lance
 */
public interface EventPublisher {

    /**
     * 发布事件
     */
    void publishEvent(Object event);
}
