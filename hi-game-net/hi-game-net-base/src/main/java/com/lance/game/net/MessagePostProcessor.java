package com.lance.game.net;

import com.lance.game.net.annotation.Message;
import com.lance.game.net.annotation.MessageHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Lance
 * @since 2021/4/25
 */
public class MessagePostProcessor implements BeanPostProcessor {

    @Autowired
    private MessageManager messageManager;

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
        Message messageAnnotation = messageClass.getAnnotation(Message.class);
        if (sessionClass != Session.class || messageAnnotation == null) {
            throw new IllegalArgumentException("Illegal argument type: " + bean.getClass().getSimpleName() + "#" + method.getName());
        }

        // 注册消息处理
        MessageDefinition messageDefinition = new MessageDefinition();
        messageDefinition.setId(messageAnnotation.value());
        messageDefinition.setClazz(messageClass);
        messageDefinition.setHandler(new MessageMethodHandler(bean, method, messageErrorHandler));
        messageManager.registerMessage(messageDefinition);
    }
}
