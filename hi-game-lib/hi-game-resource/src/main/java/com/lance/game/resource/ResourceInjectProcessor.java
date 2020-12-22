package com.lance.game.resource;

import com.lance.game.resource.annotation.GameResourceInject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 资源注入处理
 * <pre>
 *     &#64GameResourceInject
 *     private ResourceStorage&lt;Integer, ItemResource&gt; itemResourceStorage;
 * </pre>
 *
 * @author Lance
 * @since 2020/12/22
 */
public class ResourceInjectProcessor implements InstantiationAwareBeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            if (field.isAnnotationPresent(GameResourceInject.class)) {
                processBean(bean, field);
            }
        });
        return true;
    }

    private void processBean(Object bean, Field field) {
        // 校验字段类型
        Class<?> fieldType = field.getType();
        if (!fieldType.equals(ResourceStorage.class)) {
            String message = String.format("%s#%s字段类型不为ResourceStorage", bean.getClass().getName(), field.getName());
            throw new IllegalArgumentException(message);
        }

        // 校验泛型参数
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            String message = String.format("%s#%s未配置泛型参数", bean.getClass().getName(), field.getName());
            throw new IllegalArgumentException(message);
        }

        // 注入存储器
        ResourceContext resourceContext = applicationContext.getBean(ResourceContext.class);
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        Type resourceType = actualTypeArguments[1];
        ResourceStorage<?, ?> storage = resourceContext.getStorage((Class<?>) resourceType);
        if (storage == null) {
            return;
        }

        try {
            field.setAccessible(true);
            field.set(bean, storage);
        } catch (IllegalAccessException e) {
            String message = String.format("%s#%s参数注入失败", bean.getClass().getName(), field.getName());
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
