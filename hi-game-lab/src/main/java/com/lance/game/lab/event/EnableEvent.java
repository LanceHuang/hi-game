package com.lance.game.lab.event;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用事件框架
 *
 * @author Lance
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SimpleEventConfiguration.class)
public @interface EnableEvent { // todo 这个应该要做成通用
}
