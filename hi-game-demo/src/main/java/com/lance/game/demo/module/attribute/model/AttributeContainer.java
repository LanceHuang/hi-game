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
    private final Map<ModuleAttributeId, Map<AttributeType, Long>> moduleAttributeMap = new HashMap<>();

    /** 最终属性 */
    private final Map<AttributeType, Long> finalAttributeMap = new HashMap<>();

    /**
     * 添加或更新属性，并重算
     *
     * @param moduleAttributeId  模块属性id
     * @param attributeMap 添加或更新的属性
     */
    public void putAndComputeAttributes(ModuleAttributeId moduleAttributeId, Map<AttributeType, Long> attributeMap) {
        putAttributes(moduleAttributeId, attributeMap);
        compute(moduleAttributeId);
    }

    /**
     * 添加或更新属性
     *
     * @param moduleAttributeId  模块属性id
     * @param attributeMap 添加或更新的属性
     */
    public void putAttributes(ModuleAttributeId moduleAttributeId, Map<AttributeType, Long> attributeMap) {
        if (moduleAttributeId == null) {
            return;
        }

        Map<AttributeType, Long> oldAttributeMap = moduleAttributeMap.get(moduleAttributeId);
        // todo 没有旧数据
        // todo 有旧数据

        // 统计RATIO和MODULE_RATIO
    }

    /**
     * 计算属性
     *
     * @param moduleAttributeId 模块属性id
     */
    public void compute(ModuleAttributeId moduleAttributeId) {
        if (moduleAttributeId == null) {
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

    public Map<ModuleAttributeId, Map<AttributeType, Long>> getModuleAttributeMap() {
        return moduleAttributeMap;
    }

    public Map<AttributeType, Long> getFinalAttributeMap() {
        return finalAttributeMap;
    }
}
