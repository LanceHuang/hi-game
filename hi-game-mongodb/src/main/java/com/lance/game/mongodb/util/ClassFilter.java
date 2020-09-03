package com.lance.game.mongodb.util;

/**
 * @author Lance
 */
@FunctionalInterface
public interface ClassFilter {

    boolean accept(Class<?> clazz);
}
