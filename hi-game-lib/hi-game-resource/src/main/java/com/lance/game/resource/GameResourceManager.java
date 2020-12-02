package com.lance.game.resource;

import java.util.Collection;

/**
 * 游戏资源管理器
 *
 * @author Lance
 * @since 2020/12/2
 */
public class GameResourceManager {

    /**
     * 获取资源
     *
     * @param id 资源id
     */
    public <T> T get(Class<?> resourceClz, int id) {
        return null;
    }

    /**
     * 获取所有资源
     */
    public <T> Collection<T> getAll(Class<?> resourceClz) {
        return null;
    }
}
