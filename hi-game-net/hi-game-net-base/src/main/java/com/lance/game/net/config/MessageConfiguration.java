package com.lance.game.net.config;

import com.lance.game.net.annotation.Message;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.message.MessagePath;
import com.lance.game.net.message.MessagePostProcessor;
import com.lance.game.net.message.MessageScanner;
import org.springframework.context.annotation.Bean;

/**
 * 消息配置
 * <ol>
 *     <li>扫描所有标注了Message的类</li>
 *     <li>运行时生成schema</li>
 *     <li>运行时生成处理器</li>
 * </ol>
 *
 * @author Lance
 * @since 2021/4/26
 */
public class MessageConfiguration {

    @Bean
    private MessageManager messageManager(MessagePath messagePath) {
        MessageManager messageManager = new MessageManager();
        MessageScanner messageScanner = new MessageScanner(messageManager);
        messageScanner.scan(messagePath.getBasePackages(), Message.class);
        return messageManager;
    }

    @Bean
    public MessagePostProcessor messagePostProcessor() {
        return new MessagePostProcessor();
    }
}
