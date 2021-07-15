package com.lance.game.lab.event;

import com.lance.game.lab.event.annotation.EventListener;
import com.lance.game.lab.event.invoker.EventListenerInvoker;
import com.lance.game.lab.event.invoker.EventListenerInvokerFactory;
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

    private final EventListenerInvokerFactory factory;

    private final EventBus eventBus;

    public EventListenerProcessor(EventListenerInvokerFactory factory, EventBus eventBus) {
        this.factory = factory;
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
        EventListenerInvoker eventListenerInvoker = factory.createEventListenerInvoker(bean, method, parameterClass);
        eventBus.registerEventListenerInvoker(eventListenerInvoker);
    }
}
