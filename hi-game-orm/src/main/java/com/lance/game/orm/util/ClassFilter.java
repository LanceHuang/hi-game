package com.lance.game.orm.util;

/**
 * @author Lance
 */
@FunctionalInterface
public interface ClassFilter {

    boolean accept(Class<?> clazz);
}
