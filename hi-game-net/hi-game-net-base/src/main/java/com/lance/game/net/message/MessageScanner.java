package com.lance.game.net.message;

/**
 * 消息扫描
 *
 * @author Lance
 * @since 2021/4/29
 */
public class MessageScanner {

    private MessageManager messageManager;

    public MessageScanner(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    /**
     * 扫描消息类
     *
     * @param annotation 消息注解
     */
    public void scan(Class<?> annotation) {
        // todo
    }
}
