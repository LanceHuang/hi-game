package com.lance.game.net;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/4/26
 */
public class MessageManager {

    /** 消息id -> 消息定义 */
    private final Map<Integer, MessageDefinition> id2Definitions = new HashMap<>();

    /** 消息类 -> 消息定义 */
    private final Map<Class<?>, MessageDefinition> classToDefinitions = new HashMap<>();

    /**
     * 注册消息
     *
     * @param definition 消息定义
     */
    public void registerMessage(MessageDefinition definition) {
        if (this.id2Definitions.putIfAbsent(definition.getId(), definition) != null) {
            throw new IllegalArgumentException("Duplicate message id: " + definition.getId());
        }

        if (this.classToDefinitions.putIfAbsent(definition.getClazz(), definition) != null) {
            throw new IllegalArgumentException("Duplicate message class: " + definition.getClazz());
        }
    }

    /**
     * 获取消息定义
     *
     * @param id 消息id
     * @return 消息定义
     */
    public MessageDefinition getMessageDefinition(int id) {
        return this.id2Definitions.get(id);
    }

    /**
     * 获取消息定义
     *
     * @param clazz 消息类
     * @return 消息定义
     */
    public MessageDefinition getMessageDefinition(Class<?> clazz) {
        return this.classToDefinitions.get(clazz);
    }
}
