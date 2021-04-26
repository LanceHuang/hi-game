package com.lance.game.net.config;

import com.lance.game.net.MessageManager;
import com.lance.game.net.MessagePostProcessor;
import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 * @since 2021/4/26
 */
public class MessageConfiguration {

    @Bean
    private MessageManager messageManager() {
        return new MessageManager();
    }

    @Bean
    public MessagePostProcessor messagePostProcessor() {
        return new MessagePostProcessor();
    }
}
