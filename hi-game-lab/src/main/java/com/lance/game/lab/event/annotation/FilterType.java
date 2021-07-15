package com.lance.game.lab.event.annotation;

/**
 * 过滤器类型
 *
 * @author Lance
 * @since 2021/7/15
 */
public enum FilterType {

    /** 指定类型 */
    ASSIGNABLE,

    /** 指定注解 */
    ANNOTATION,

    /** 包名正则 */
    REGEX,

    /** 自定义 */
    CUSTOM,

    ;
}
