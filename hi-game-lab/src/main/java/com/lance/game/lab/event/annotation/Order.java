package com.lance.game.lab.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件执行顺序
 *
 * @author Lance
 * @since 2021/7/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    /**
     * 执行顺序
     */
    int value() default Integer.MAX_VALUE;
}
