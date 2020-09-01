package com.lance.game.lab.event;

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
    public EventMulticaster eventMulticaster() {
        return new SimpleEventMulticaster();
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new SimpleEventPublisher();
    }
}
