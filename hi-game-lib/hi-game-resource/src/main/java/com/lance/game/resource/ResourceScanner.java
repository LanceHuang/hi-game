package com.lance.game.resource;

import com.lance.game.resource.annotation.GameResource;
import com.lance.game.resource.config.ResourceProperties;
import com.lance.game.resource.util.ClassUtils;

import java.lang.reflect.Modifier;
import java.util.Collection;

/**
 * 资源扫描
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceScanner {

    private ResourceContext context;

    private ResourceProperties properties;

    /**
     * 扫描标注了相应注解的类
     */
    public void scan() {
        // 扫描标注了相应注解的类
        Collection<Class<?>> scanResult = ClassUtils.scan(properties.getBasePackage(), clazz -> !clazz.isInterface()
                && !Modifier.isAbstract(clazz.getModifiers())
                && clazz.isAnnotationPresent(GameResource.class));

        // 注册
        for (Class<?> resourceClass : scanResult) {
            String resourcePath = resolveResourcePath(resourceClass);
            ResourceDefinition definition = new ResourceDefinition(resourceClass, properties.getType(), resourcePath);
            context.registerStorage(definition);
        }
    }

    /**
     * 解析资源路径
     *
     * @param resourceClass 资源类
     */
    private String resolveResourcePath(Class<?> resourceClass) {
        // resource/ItemResource.xlsx
        return properties.getResourcePath() + resourceClass.getSimpleName() + "." + properties.getSuffix();
    }

    public void setContext(ResourceContext context) {
        this.context = context;
    }

    public void setProperties(ResourceProperties properties) {
        this.properties = properties;
    }
}
