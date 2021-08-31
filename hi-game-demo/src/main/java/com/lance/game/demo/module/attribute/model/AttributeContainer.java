package com.lance.game.demo.module.attribute.model;

import com.lance.game.common.util.CollectionUtils;
import com.lance.game.demo.module.attribute.constant.AttributeType;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 属性容器
 *
 * @author Lance
 * @since 2021/8/31
 */
public class AttributeContainer {

    /** 模块属性 */
    private final Map<ModuleAttributeId, Map<AttributeType, Long>> moduleAttributeMap = CollectionUtils.hashMap();

    /** 最终属性 */
    private Map<AttributeType, Long> finalAttributeMap = CollectionUtils.hashMap(0);

    /**
     * 更新并计算属性
     */
    public void updateAndComputeAttributes(ModuleAttributeId mai, Map<AttributeType, Long> newAttrMap) {
        updateAttributes(mai, newAttrMap);
        compute();
    }

    /**
     * 更新属性
     */
    public void updateAttributes(ModuleAttributeId mai, Map<AttributeType, Long> newAttrMap) {
        this.moduleAttributeMap.put(mai, newAttrMap);
    }

    /**
     * 计算属性
     */
    public void compute() {
        Map<AttributeType, Long> tempAttributeMap = CollectionUtils.hashMap(this.finalAttributeMap.size());
        for (Map<AttributeType, Long> attrMap : this.moduleAttributeMap.values()) {
            for (Map.Entry<AttributeType, Long> entry : attrMap.entrySet()) {
                long oldValue = MapUtils.getLongValue(tempAttributeMap, entry.getKey(), 0L);
                long newValue = entry.getKey().getAttributeOperate().operate(oldValue, entry.getValue());
                tempAttributeMap.put(entry.getKey(), newValue);
            }
        }
        this.finalAttributeMap = tempAttributeMap;
    }

    public Map<ModuleAttributeId, Map<AttributeType, Long>> getModuleAttributeMap() {
        return moduleAttributeMap;
    }

    public Map<AttributeType, Long> getFinalAttributeMap() {
        return finalAttributeMap;
    }
}
