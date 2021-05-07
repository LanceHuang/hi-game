package com.lance.game.net.message;

import com.lance.game.net.session.Session;

import java.lang.reflect.Method;

/**
 * @author Lance
 * @since 2021/4/26
 */
public class DefaultMessageErrorHandler implements MessageErrorHandler {

    @Override
    public void handleError(Session session, Object bean, Method method, Object msg, Exception e) {
        e.printStackTrace();
    }
}
