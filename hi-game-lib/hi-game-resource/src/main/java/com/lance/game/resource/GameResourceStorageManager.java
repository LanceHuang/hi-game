package com.lance.game.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源存储管理
 *
 * @author Lance
 * @since 2020/12/3
 */
public class GameResourceStorageManager {

    /** 资源存储器映射：com.lance.item.resource.ItemResource -> storage */
    private final Map<Class<?>, GameResourceStorage<?, ?>> storageMap = new HashMap<>();

    /** 资源简称映射：ItemResource -> com.lance.item.resource.ItemResource */
    private final Map<String, Class<?>> simpleNameMap = new HashMap<>();

    /**
     * 添加定义
     */
    public void addDefinition(GameResourceDefinition definition) {
        GameResourceStorage<?, ?> gameResourceStorage = new GameResourceStorage<>(definition);
        gameResourceStorage.init();
        registerStorage(definition.getClazz(), gameResourceStorage);
    }

    /**
     * 注册资源存储器
     *
     * @param clazz   资源类
     * @param storage 资源存储器
     */
    public void registerStorage(Class<?> clazz, GameResourceStorage<?, ?> storage) {
        this.storageMap.put(clazz, storage);
        this.simpleNameMap.put(clazz.getSimpleName(), clazz);
    }

    /**
     * 获取资源存储器
     *
     * @param clazz 资源类
     */
    public GameResourceStorage<?, ?> getStorage(Class<?> clazz) {
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
