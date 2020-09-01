package com.lance.game.lab.event;

/**
 * @author Lance
 */
public interface IEventBusService {

    /**
     * 发布事件
     */
    void publishEvent(Object event);
}
