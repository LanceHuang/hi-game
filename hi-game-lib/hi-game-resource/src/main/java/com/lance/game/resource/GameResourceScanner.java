package com.lance.game.resource;

import com.lance.game.resource.annotation.GameResource;
import com.lance.game.resource.util.ClassUtils;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Modifier;
import java.util.Collection;

/**
 * 资源扫描器
 *
 * @author Lance
 * @since 2020/12/3
 */
public class GameResourceScanner {

    /** 资源配置路径 */
    private static final String resourcePath = "resource/";

    private ApplicationContext applicationContext;

    /** 扫描路径 */
    private String basePackage;

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
        GameResourceStorageManager gameResourceStorageManager = applicationContext.getBean(GameResourceStorageManager.class);
        for (Class<?> resourceClass : scanResult) {
            String resourcePath = resolveResourcePath(resourceClass);
            GameResourceDefinition definition = new GameResourceDefinition(resourceClass, resourcePath);
            gameResourceStorageManager.addDefinition(definition);
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

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
