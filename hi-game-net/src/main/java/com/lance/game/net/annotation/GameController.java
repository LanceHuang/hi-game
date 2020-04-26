package com.lance.game.net.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 游戏控制器注解。
 * 注解的类会扫描其中注解了{@code com.lance.net.annotation.GameHandler}的方法，自动加try-catch处理
 *
 * @author Lance
 * @since 2019-10-23 20:47:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface GameController {

    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * 模块id
     */
    int module() default 0;
}
