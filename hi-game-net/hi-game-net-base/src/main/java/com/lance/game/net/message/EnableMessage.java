package com.lance.game.net.message;

import com.lance.game.net.config.MessageConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lance
 * @since 2021/4/29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MessagePath.Registrar.class, MessageConfiguration.class})
public @interface EnableMessage {

    /** 扫描包 */
    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};
}
