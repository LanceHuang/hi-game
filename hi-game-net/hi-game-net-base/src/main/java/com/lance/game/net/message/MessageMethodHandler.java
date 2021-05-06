package com.lance.game.net.message;

import com.lance.game.net.DefaultMessageErrorHandler;
import com.lance.game.net.MessageErrorHandler;
import com.lance.game.net.Session;

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

    private MessageErrorHandler messageErrorHandler = new DefaultMessageErrorHandler();

    public MessageMethodHandler(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
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
