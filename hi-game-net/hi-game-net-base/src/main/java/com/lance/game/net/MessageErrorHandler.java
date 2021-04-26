package com.lance.game.net;

import java.lang.reflect.Method;

/**
 * 消息异常处理
 *
 * @author Lance
 * @since 2021/4/26
 */
public interface MessageErrorHandler {

    /**
     * 处理异常
     *
     * @param session 会话
     * @param bean    监听者
     * @param method  监听方法
     * @param msg     消息
     * @param e       异常
     */
    void handleError(Session session, Object bean, Method method, Object msg, Exception e);
}
