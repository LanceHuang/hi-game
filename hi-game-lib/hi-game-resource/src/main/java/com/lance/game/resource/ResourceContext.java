package com.lance.game.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源上下文
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceContext {

    /** 资源存储器映射：com.lance.item.resource.ItemResource -> storage */
    private final Map<Class<?>, ResourceStorage<?, ?>> storageMap = new HashMap<>();

    /** 资源简称映射：ItemResource -> com.lance.item.resource.ItemResource */
    private final Map<String, Class<?>> simpleNameMap = new HashMap<>();

    /**
     * 注册资源存储器
     */
    public void registerStorage(ResourceDefinition definition) {
        ResourceStorage<?, ?> resourceStorage = new ResourceStorage<>(definition);
        resourceStorage.init();
        addStorage(definition.getClazz(), resourceStorage);
    }

    /**
     * 添加资源存储器
     *
     * @param clazz   资源类
     * @param storage 资源存储器
     */
    private void addStorage(Class<?> clazz, ResourceStorage<?, ?> storage) {
        this.storageMap.put(clazz, storage);
        this.simpleNameMap.put(clazz.getSimpleName(), clazz);
    }

    /**
     * 获取资源存储器
     *
     * @param clazz 资源类
     */
    public ResourceStorage<?, ?> getStorage(Class<?> clazz) {
        return this.storageMap.get(clazz);
    }

    /**
     * 热更资源
     *
     * @param clazz 资源类
     */
    public void reload(Class<?> clazz) {
        this.storageMap.get(clazz).init();
    }

    /**
     * 热更资源
     *
     * @param className 资源类名
     */
    public void reload(String className) {
        Class<?> clazz = this.simpleNameMap.get(className);
        this.storageMap.get(clazz).init();
    }
}
