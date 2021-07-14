package com.lance.game.lab.event.config;

import com.lance.game.lab.event.EventBus;
import com.lance.game.lab.event.EventListenerInvokerFactory;
import com.lance.game.lab.event.EventListenerProcessor;
import com.lance.game.lab.event.executor.AsyncEventListenerExecutor;
import com.lance.game.lab.event.executor.SyncEventListenerExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class EventConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SyncEventListenerExecutor syncEventListenerExecutor() {
        return new SyncEventListenerExecutor();
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncEventListenerExecutor asyncEventListenerExecutor() {
        return new AsyncEventListenerExecutor();
    }

    @Bean
    public EventListenerInvokerFactory eventListenerInvokerFactory() {
        return new EventListenerInvokerFactory();
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    public EventListenerProcessor eventListenerProcessor(EventListenerInvokerFactory factory, EventBus eventBus) {
        return new EventListenerProcessor(factory, eventBus);
    }
}
