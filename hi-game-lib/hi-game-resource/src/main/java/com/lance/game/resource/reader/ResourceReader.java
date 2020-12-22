package com.lance.game.resource.reader;

import java.util.Iterator;

/**
 * 资源读取
 *
 * @author Lance
 * @since 2020/12/3
 */
public interface ResourceReader {

    /**
     * 读取配置资源
     *
     * @param path  资源路径
     * @param clazz 资源类
     */
    <T> Iterator<T> read(String path, Class<T> clazz);
}
