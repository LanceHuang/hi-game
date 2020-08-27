package com.lance.game.lab.event;

import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;

/**
 * @author Lance
 */
public class SpringEventPublisher implements EventPublisher {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(Object event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
