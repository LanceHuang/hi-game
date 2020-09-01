package com.lance.game.lab.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 注册事件处理器
 *
 * @author Lance
 */
public class EventHandlerProcessor implements InstantiationAwareBeanPostProcessor {

    @Resource
    private EventContext eventContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithMethods(bean.getClass(), method -> {
            EventListener annotation = method.getAnnotation(EventListener.class);
            if (annotation != null) {
                parseEventHandler(bean, method);
            }
        });

        return bean;
    }

    /**
     * 解析事件处理器
     */
    private void parseEventHandler(Object bean, Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException();
        }

        eventContext.addEventHandler(new SimpleEventHandler(bean, method, parameterTypes[0]));
    }
}
