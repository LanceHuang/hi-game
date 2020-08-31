package com.lance.game.lab.event;

import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 */
public class SimpleEventConfiguration {

    @Bean
    public EventContext eventContext() {
        return new EventContext();
    }

    @Bean
    public EventHandlerProcessor eventHandlerProcessor() {
        return new EventHandlerProcessor();
    }

    @Bean
    public EventPublisher eventPublisher() {
        SimpleEventPublisher simpleEventPublisher = new SimpleEventPublisher();
        simpleEventPublisher.setEventMulticaster(new SimpleEventMulticaster());
        return simpleEventPublisher;
    }
}
