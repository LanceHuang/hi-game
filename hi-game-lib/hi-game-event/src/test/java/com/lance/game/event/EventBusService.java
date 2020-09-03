package com.lance.game.event;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class EventBusService implements IEventBusService {

    @Resource
    public EventPublisher eventPublisher;

    @Override
    public void publishEvent(Object event) {
        this.eventPublisher.publishEvent(event);
    }
}
