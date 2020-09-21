package com.lance.game.event;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

/**
 * 任务事件配置
 *
 * @author Lance
 */
public class TaskEventConfiguration {

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
        TaskEventMulticaster taskEventMulticaster = new TaskEventMulticaster(eventContext);
        taskEventMulticaster.setTaskExecutor(Executors.newSingleThreadExecutor());
        return taskEventMulticaster;
    }

    @Bean
    public EventPublisher eventPublisher(EventMulticaster eventMulticaster) {
        SimpleEventPublisher simpleEventPublisher = new SimpleEventPublisher();
        simpleEventPublisher.setEventMulticaster(eventMulticaster);
        return simpleEventPublisher;
    }
}
