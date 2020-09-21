package com.lance.game.event;

import org.springframework.context.annotation.Bean;

/**
 * 常见事件配置
 *
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
    public EventMulticaster eventMulticaster(EventContext eventContext) {
        return new SimpleEventMulticaster(eventContext);
    }

    @Bean
    public EventPublisher eventPublisher(EventMulticaster eventMulticaster) {
        SimpleEventPublisher simpleEventPublisher = new SimpleEventPublisher();
        simpleEventPublisher.setEventMulticaster(eventMulticaster);
        return simpleEventPublisher;
    }
}
