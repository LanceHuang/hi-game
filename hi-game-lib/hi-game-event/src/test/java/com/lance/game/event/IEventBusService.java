package com.lance.game.event;

/**
 * 事件总线
 *
 * @author Lance
 */
public interface IEventBusService {

    /**
     * 发布事件
     */
    void publishEvent(Object event);
}
