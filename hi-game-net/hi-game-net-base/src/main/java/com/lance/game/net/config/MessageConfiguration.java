package com.lance.game.net.config;

import com.lance.game.net.MessageProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 * @since 2021/4/26
 */
@EnableConfigurationProperties(MessageProperties.class)
public class MessageConfiguration {

    @Bean
    public MessageProcessor messageProcessor() {
        return new MessageProcessor();
    }
}
