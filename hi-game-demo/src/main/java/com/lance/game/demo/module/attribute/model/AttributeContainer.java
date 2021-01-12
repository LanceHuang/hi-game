package com.lance.game.demo.module.attribute.model;

import com.lance.game.demo.module.attribute.constant.AttributeType;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性容器
 *
 * @author Lance
 */
public class AttributeContainer {

    /** 模块属性 */
    private final Map<AttributeId, Map<AttributeType, Long>> moduleAttributeMap = new HashMap<>();

    /** 最终属性 */
    private final Map<AttributeType, Long> finalAttributeMap = new HashMap<>();

    /**
     * 添加或更新属性，并重算
     *
     * @param attributeId  模块属性id
     * @param attributeMap 添加或更新的属性
     */
    public void putAndComputeAttributes(AttributeId attributeId, Map<AttributeType, Long> attributeMap) {
        // todo

        // todo 计算属性
    }

    /**
     * 添加或更新属性
     *
     * @param attributeId  模块属性id
     * @param attributeMap 添加或更新的属性
     */
    public void putAttributes(AttributeId attributeId, Map<AttributeType, Long> attributeMap) {
        // todo
    }

    /**
     * 计算属性
     */
    public void compute() {
        // todo
    }

    public Map<AttributeId, Map<AttributeType, Long>> getModuleAttributeMap() {
        return moduleAttributeMap;
    }

    public Map<AttributeType, Long> getFinalAttributeMap() {
        return finalAttributeMap;
    }
}
