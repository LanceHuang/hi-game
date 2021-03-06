package com.lance.game.event;

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
            throw new IllegalArgumentException(
                    bean.getClass().getName() + "#" + method.getName() + "事件监听方法注册失败，期望参数长度：1");
        }

        Class<?> handleEventType = parameterTypes[0]; // 处理事件类型
        this.eventContext.registerEventHandler(handleEventType, new SimpleEventHandler(bean, method, handleEventType));
    }
}
