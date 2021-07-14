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
            throw new IllegalArgumentException("Illegal argument size: " + bean.getClass().getName() + "#" + method.getName());
        }

        // 参数类型
        Class<?> parameterClass = parameters[0].getType();
        if (!Event.class.isAssignableFrom(parameterClass)) {
            throw new IllegalArgumentException("Illegal argument type: " + bean.getClass().getName() + "#" + method.getName()
                    + " " + parameterClass.getName());
        }

        // 参数值
        EventListener eventListener = method.getAnnotation(EventListener.class);
        if (eventListener.value().length != 0) {
            for (Class<?> eventClass : eventListener.value()) {
                if (!parameterClass.isAssignableFrom(eventClass)) {
                    throw new IllegalArgumentException("Illegal event type: " + bean.getClass().getName() + "#" + method.getName()
                            + " " + eventClass.getName());
                }
            }
        }

        EventListenerInvoker eventListenerInvoker = factory.createEventListenerInvoker(bean, method, parameterClass);
        eventBus.addEventListenerInvoker(eventListenerInvoker);
    }
}
