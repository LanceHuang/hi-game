package com.lance.game.net.schema;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * schema管理
 *
 * @author Lance
 * @since 2021/4/28
 */
@Slf4j
public class MessageSchemaManager {

    /** 消息类 -> schema */
    private final Map<Class<?>, MessageSchema> schemaMap = new ConcurrentHashMap<>();

    /**
     * 获取schema
     *
     * @param clazz 消息类
     * @return schema
     */
    public MessageSchema getSchema(Class<?> clazz) {
        MessageSchema schema = schemaMap.get(clazz);
        if (schema == null) {
            return schemaMap.computeIfAbsent(clazz, this::createSchema);
        }
        return schema;
    }

    /**
     * 创建schema
     *
     * @param clazz 消息类
     * @return schema
     */
    public MessageSchema createSchema(Class<?> clazz) {
        try {
            return MessageSchemaUtils.enhance(clazz);
        } catch (Exception e) {
            log.error("Failed to create schema: {}", clazz.getName(), e);
        }
        return null;
    }
}
