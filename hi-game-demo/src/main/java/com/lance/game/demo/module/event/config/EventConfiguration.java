package com.lance.game.demo.module.event.config;

import com.lance.game.event.EventContext;
import com.lance.game.event.EventHandlerProcessor;
import com.lance.game.event.EventMulticaster;
import com.lance.game.event.EventPublisher;
import com.lance.game.event.SimpleEventMulticaster;
import com.lance.game.event.SimpleEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 事件配置
 *
 * @author Lance
 */
@Configuration
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
