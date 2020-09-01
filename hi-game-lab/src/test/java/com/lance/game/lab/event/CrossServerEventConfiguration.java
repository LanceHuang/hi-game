package com.lance.game.lab.event;

import org.springframework.context.annotation.Bean;

/**
 * 跨服事件配置
 *
 * @author Lance
 */
public class CrossServerEventConfiguration {

    @Bean
    public EventPublisher eventPublisher() {
        return new CrossServerEventPublisher();
    }
}
