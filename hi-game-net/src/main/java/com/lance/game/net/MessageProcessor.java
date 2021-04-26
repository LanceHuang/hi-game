package com.lance.game.net;

import com.lance.game.net.annotation.Message;
import com.lance.game.net.annotation.MessageListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Lance
 * @since 2021/4/25
 */
public class MessageProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithLocalMethods(bean.getClass(), method -> process(bean, beanName, method));
        return bean;
    }

    private void process(Object bean, String beanName, Method method) {
        if (method.isAnnotationPresent(MessageListener.class)) {
            return;
        }

        // 参数校验
        Parameter[] parameters = method.getParameters();
        if (parameters.length != 2) {
            throw new IllegalArgumentException("标注了MessageListener的方法，参数格式必须为(Session, XX)");
        }
        Class<?> sessionClass = parameters[0].getType();
        Class<?> messageClass = parameters[1].getType();
        if (sessionClass != Session.class || !messageClass.isAnnotationPresent(Message.class)) {
            throw new IllegalArgumentException("标注了MessageListener的方法，参数格式必须为(Session, XX)");
        }

        // 注册消息处理

    }
}
