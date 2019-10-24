package com.lance.net.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通讯协议
 *
 * @author Lance
 * @since 2019/10/23 17:57
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Protocol {

    /** 协议号 */
    int value();
}
