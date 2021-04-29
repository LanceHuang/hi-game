package com.lance.game.net.message;

import com.lance.game.net.DefaultMessageErrorHandler;
import com.lance.game.net.MessageErrorHandler;
import com.lance.game.net.Session;
import com.lance.game.net.annotation.MessageHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Lance
 * @since 2021/4/25
 */
public class MessagePostProcessor implements BeanPostProcessor {

    private MessageErrorHandler messageErrorHandler = new DefaultMessageErrorHandler(); // todo

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithLocalMethods(bean.getClass(), method -> process(bean, method));
        return bean;
    }

    private void process(Object bean, Method method) {
        if (!method.isAnnotationPresent(MessageHandler.class)) {
            return;
        }

        // 参数校验
        Parameter[] parameters = method.getParameters();
        if (parameters.length != 2) {
            throw new IllegalArgumentException("Illegal argument length: " + bean.getClass().getSimpleName() + "#" + method.getName());
        }
        Class<?> sessionClass = parameters[0].getType();
        Class<?> messageClass = parameters[1].getType();
        if (sessionClass != Session.class) {
            throw new IllegalArgumentException("Illegal argument type: " + bean.getClass().getSimpleName() + "#" + method.getName());
        }

        // 注册消息处理
        MessageDefinition messageDefinition = MessageManager.getInstance().getMessageDefinition(messageClass);
        if (messageDefinition == null) {
            throw new IllegalArgumentException("Unhandled argument type: " + bean.getClass().getSimpleName() + "#" + method.getName());
        }
        if (messageDefinition.getHandler() != null) {
            throw new IllegalArgumentException("Repeat processing type: " + bean.getClass().getSimpleName() + "#" + method.getName());
        }

        messageDefinition.setHandler(new MessageMethodHandler(bean, method, messageErrorHandler));
    }
}
