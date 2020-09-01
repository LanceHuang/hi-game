package com.lance.game.lab.event;

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
    public EventMulticaster eventMulticaster() {
        TaskEventMulticaster taskEventMulticaster = new TaskEventMulticaster();
        taskEventMulticaster.setTaskExecutor(Executors.newSingleThreadExecutor());
        return taskEventMulticaster;
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new SimpleEventPublisher();
    }
}
