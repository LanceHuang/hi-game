package com.lance.game.demo.module.attribute.model;

import com.lance.game.common.util.CollectionUtils;
import com.lance.game.demo.module.attribute.computer.AttributeComputer;
import com.lance.game.demo.module.attribute.computer.AttributeComputers;
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
    private final Map<ModuleId, Map<AttributeType, Long>> moduleAttributeMap = CollectionUtils.hashMap();

    /** 白值属性 */
    private Map<AttributeType, Long> originAttributeMap = CollectionUtils.hashMap(0);

    /** 最终属性 */
    private Map<AttributeType, Long> finalAttributeMap = CollectionUtils.hashMap(0);

    /**
     * 更新并计算属性
     *
     * @param moduleId   模块id
     * @param newAttrMap 模块属性
     */
    public void updateAndComputeAttributes(ModuleId moduleId, Map<AttributeType, Long> newAttrMap) {
        updateAttributes(moduleId, newAttrMap);
        compute();
    }

    /**
     * 更新属性
     *
     * @param moduleId   模块id
     * @param newAttrMap 模块属性
     */
    public void updateAttributes(ModuleId moduleId, Map<AttributeType, Long> newAttrMap) {
        this.moduleAttributeMap.put(moduleId, newAttrMap);
    }

    /**
     * 计算属性
     */
    public void compute() {
        // 先算白值
        Map<AttributeType, Long> tempOriginAttributeMap = CollectionUtils.hashMap(this.originAttributeMap.size());
        for (Map<AttributeType, Long> attrMap : this.moduleAttributeMap.values()) {
            for (Map.Entry<AttributeType, Long> entry : attrMap.entrySet()) {
                long oldValue = MapUtils.getLongValue(tempOriginAttributeMap, entry.getKey(), 0L);
                long newValue = entry.getKey().getOperator().operate(oldValue, entry.getValue());
                tempOriginAttributeMap.put(entry.getKey(), newValue);
            }
        }

        // 根据白值，计算最终值
        Map<AttributeType, Long> tempFinalAttributeMap = CollectionUtils.hashMap(tempOriginAttributeMap.size());
        for (Map.Entry<AttributeType, AttributeComputer> entry : AttributeComputers.getAllComputer().entrySet()) {
            AttributeType type = entry.getKey();
            AttributeComputer attributeComputer = entry.getValue();

            long value = MapUtils.getLongValue(tempOriginAttributeMap, type, 0L);
            long newValue = attributeComputer.compute(this.moduleAttributeMap, tempOriginAttributeMap, type, value);
            tempFinalAttributeMap.put(type, newValue);
        }

        // 覆盖
        this.originAttributeMap = tempOriginAttributeMap;
        this.finalAttributeMap = tempFinalAttributeMap;
    }

    public Map<ModuleId, Map<AttributeType, Long>> getModuleAttributeMap() {
        return moduleAttributeMap;
    }

    public Map<AttributeType, Long> getOriginAttributeMap() {
        return originAttributeMap;
    }

    public Map<AttributeType, Long> getFinalAttributeMap() {
        return finalAttributeMap;
    }
}
