package com.lance.game.mongodb.util;

/**
 * @author Lance
 */
public class DefaultClassFilter implements ClassFilter {

    @Override
    public boolean accept(Class<?> clazz) {
        return true;
    }
}
