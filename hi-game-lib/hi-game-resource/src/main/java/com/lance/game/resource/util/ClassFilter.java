package com.lance.game.resource.util;

/**
 * 类过滤器
 *
 * @author Lance
 * @since 2020/12/3
 */
public interface ClassFilter {

    /**
     * 判断该类是否满足条件
     */
    boolean accept(Class<?> clazz);
}
