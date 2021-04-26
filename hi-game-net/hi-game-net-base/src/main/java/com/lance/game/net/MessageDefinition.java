package com.lance.game.net;

import lombok.Getter;
import lombok.Setter;

/**
 * 消息定义
 *
 * @author Lance
 * @since 2021/4/26
 */
@Getter
@Setter
public class MessageDefinition {

    /** 消息id */
    private int id;

    /** 消息类 */
    private Class<?> clazz;

    /** 消息处理 */
    private MessageMethodHandler handler;

}
