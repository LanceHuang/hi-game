package com.lance.game.lab.event;

import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 */
public class EventConfiguration {

    @Bean
    public EventContext eventContext() {
        return new EventContext();
    }

    @Bean
    public EventHandlerProcessor eventHandlerProcessor() {
        return new EventHandlerProcessor();
    }

    @Bean
    public EventMulticaster eventMulticaster() {
        return new SimpleEventMulticaster();
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new SimpleEventPublisher();
    }
}
