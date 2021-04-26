package com.lance.game.net;

import java.lang.reflect.Method;

/**
 * 消息方法处理
 *
 * @author Lance
 * @since 2021/4/26
 */
public class MessageMethodHandler {

    private Object bean;

    private Method method;

    private MessageErrorHandler messageErrorHandler;

    public MessageMethodHandler(Object bean, Method method, MessageErrorHandler messageErrorHandler) {
        this.bean = bean;
        this.method = method;
        this.messageErrorHandler = messageErrorHandler;
    }

    /**
     * 处理消息
     *
     * @param session 会话
     * @param msg     消息
     */
    public void handle(Session session, Object msg) {
        try {
            method.invoke(bean, session, msg);
        } catch (Exception e) {
            messageErrorHandler.handleError(session, bean, method, msg, e);
        }
    }
}
