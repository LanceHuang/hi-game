package com.lance.game.lab.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件过滤器
 *
 * @author Lance
 * @since 2021/7/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Filter {

    /**
     * 过滤器类型
     */
    FilterType type();

    Class<?>[] classes() default {};

    String[] patten() default {};
}
