package com.lance.game.resource;

import com.lance.game.resource.annotation.GameResource;
import com.lance.game.resource.util.ClassUtils;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Modifier;
import java.util.Collection;

/**
 * 资源扫描
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceScanner {

    private ApplicationContext applicationContext;

    /** 资源包 */
    private String basePackage;

    /** 资源配置路径 */
    private String resourcePath;

    /** 资源文件后缀 */
    private String suffix;

    /**
     * 扫描标注了相应注解的类
     */
    public void scan() {
        // 扫描标注了相应注解的类
        Collection<Class<?>> scanResult = ClassUtils.scan(basePackage, clazz -> !clazz.isInterface()
                && !Modifier.isAbstract(clazz.getModifiers())
                && clazz.isAnnotationPresent(GameResource.class));

        // 注册
        ResourceStorageManager resourceStorageManager = applicationContext.getBean(ResourceStorageManager.class);
        for (Class<?> resourceClass : scanResult) {
            String resourcePath = resolveResourcePath(resourceClass);
            ResourceDefinition definition = new ResourceDefinition(resourceClass, resourcePath);
            resourceStorageManager.addDefinition(definition);
        }
    }

    /**
     * 解析资源路径
     *
     * @param resourceClass 资源类
     */
    private String resolveResourcePath(Class<?> resourceClass) {
        // resource/ItemResource.xlsx
        return resourcePath + resourceClass.getSimpleName() + "." + suffix;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
