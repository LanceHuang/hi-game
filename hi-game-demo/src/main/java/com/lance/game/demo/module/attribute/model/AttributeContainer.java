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
        putAttributes(attributeId, attributeMap);
        compute(attributeId);
    }

    /**
     * 添加或更新属性
     *
     * @param attributeId  模块属性id
     * @param attributeMap 添加或更新的属性
     */
    public void putAttributes(AttributeId attributeId, Map<AttributeType, Long> attributeMap) {
        if (attributeId == null) {
            return;
        }

        Map<AttributeType, Long> oldAttributeMap = moduleAttributeMap.get(attributeId);
        // todo 没有旧数据
        // todo 有旧数据

        // 统计RATIO和MODULE_RATIO
    }

    /**
     * 计算属性
     *
     * @param attributeId 模块属性id
     */
    public void compute(AttributeId attributeId) {
        if (attributeId == null) {
            return;
        }
        // todo
    }

    /**
     * 计算属性
     */
    public void compute() {
        moduleAttributeMap.keySet().forEach(this::compute);
    }

    public Map<AttributeId, Map<AttributeType, Long>> getModuleAttributeMap() {
        return moduleAttributeMap;
    }

    public Map<AttributeType, Long> getFinalAttributeMap() {
        return finalAttributeMap;
    }
}
