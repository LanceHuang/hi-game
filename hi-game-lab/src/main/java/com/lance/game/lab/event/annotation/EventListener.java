package com.lance.game.lab.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件监听
 *
 * @author Lance
 * @since 2021/7/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {

    /**
     * 过滤器类型
     */
    FilterType type() default FilterType.ASSIGNABLE;

    /**
     * 事件类型
     */
    Class<?>[] value() default {};

    /**
     * 事件正则
     */
    String[] pattern() default {};

    /**
     * 执行条件类型
     */
    EventConditionType conditionType() default EventConditionType.MVEL;

    /**
     * 条件值
     */
    String condition() default "";
}
