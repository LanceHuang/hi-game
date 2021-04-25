package com.lance.game.demo.module.event.service;

/**
 * 事件服务
 *
 * @author Lance
 */
public interface IEventService {

    /**
     * 发布事件
     *
     * @param event 事件实体
     */
    void publishEvent(Object event);
}
