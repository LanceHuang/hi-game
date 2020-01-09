package com.lance.net.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 游戏业务处理器注解。
 *
 * @author Lance
 * @since 2019-10-23 20:47:31
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameHandler {

    /**
     * 协议号
     */
    int value();
}
