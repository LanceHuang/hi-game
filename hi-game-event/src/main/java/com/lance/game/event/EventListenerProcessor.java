package com.lance.game.event;

import com.lance.game.event.annotation.EventListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class EventListenerProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    private final EventBus eventBus;

    public EventListenerProcessor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithLocalMethods(bean.getClass(), method -> {
            if (!method.isAnnotationPresent(EventListener.class)) {
                return;
            }
            registerEventListenerInvoker(bean, method);
        });
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    private void registerEventListenerInvoker(Object bean, Method method) {
        // 参数长度
        Parameter[] parameters = method.getParameters();
        if (parameters.length != 1) {
            throw new IllegalArgumentException("Unexpected argument length: " + bean.getClass().getName() + "#" + method.getName());
        }

        // 参数类型
        Class<?> parameterClass = parameters[0].getType();
        if (!Event.class.isAssignableFrom(parameterClass)) {
            throw new IllegalArgumentException("Unexpected argument type: " + bean.getClass().getName() + "#" + method.getName()
                    + " " + parameterClass.getName());
        }

        // 注册
        eventBus.registerEventListenerInvoker(bean, method);
    }
}
