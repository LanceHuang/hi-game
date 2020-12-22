package com.lance.game.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * 资源存储
 *
 * @param <K> 主键类型
 * @param <V> 资源类型
 * @author Lance
 * @since 2020/12/2
 */
public class ResourceStorage<K, V> {

    /** 资源定义 */
    private ResourceDefinition resourceDefinition;

    /** 资源集合 */
    private Map<K, V> data;

    public ResourceStorage(ResourceDefinition resourceDefinition) {
        this.resourceDefinition = resourceDefinition;
    }

    /**
     * 热更资源
     */
    public void reload() {
        // todo 读取配置，生成新data
        // todo 直接覆盖原data

        // todo
    }

    /**
     * 获取资源
     *
     * @param id 资源id
     */
    public V get(K id) {
        return data.get(id);
    }

    /**
     * 获取所有资源
     */
    public Collection<V> getAll() {
        return Collections.unmodifiableCollection(data.values());
    }

}
