package com.lance.game.demo.module.event.service;

import com.lance.game.event.EventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class EventService implements IEventService {

    @Resource
    private EventPublisher eventPublisher;

    @Override
    public void publishEvent(Object event) {
        this.eventPublisher.publishEvent(event);
    }
}
