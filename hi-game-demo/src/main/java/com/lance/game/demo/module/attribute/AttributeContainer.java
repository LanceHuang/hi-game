package com.lance.game.demo.module.attribute;

import com.lance.game.demo.module.attribute.computer.AbstractAttributeComputer;
import com.lance.game.demo.module.attribute.computer.IAttributeComputer;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性面板
 *
 * @author Lance
 */
public class AttributeContainer {

    /** 原始属性 */
    private Map<ModelAttributeId, Map<AttributeType, Long>> rawAttributeMap = new HashMap<>();

    /** 最终属性 */
    private Map<AttributeType, Long> finalAttributeMap = new HashMap<>();

    /**
     * 添加模块属性
     *
     * @param mai          模块属性id
     * @param attributeMap 模块属性表
     */
    public void putModelAttributes(ModelAttributeId mai, Map<AttributeType, Long> attributeMap) {
        if (mai == null || attributeMap == null) {
            return;
        }
        this.rawAttributeMap.put(mai, attributeMap);
    }

    /**
     * 计算属性
     */
    public void compute() {
        // 1. flat
        Map<AttributeType, Long> computeAttributeMap = new HashMap<>();
        for (Map<AttributeType, Long> map : this.rawAttributeMap.values()) {
            for (Map.Entry<AttributeType, Long> entry : map.entrySet()) {
                AttributeType type = entry.getKey();
                Long value = entry.getValue();
                if (type == null || value == null) {
                    continue;
                }

                computeAttributeMap.merge(type, value, Long::sum);
            }
        }

        // 2. compute
        Map<AttributeType, Long> tempAttributeMap = new HashMap<>();
        Map<AttributeType, IAttributeComputer> attributeComputers = AbstractAttributeComputer.getAttributeComputers();
        for (Map.Entry<AttributeType, Long> entry : computeAttributeMap.entrySet()) {
            AttributeType type = entry.getKey(); // 属性类型
            if (attributeComputers.containsKey(type)) { // 需要计算的属性
                long value = attributeComputers.get(type).compute(computeAttributeMap);
                tempAttributeMap.put(type, value);
            } else {
                tempAttributeMap.put(type, entry.getValue());
            }
        }

        // 3. replace
        this.finalAttributeMap = tempAttributeMap;
    }

    /**
     * 打印最终属性
     */
    public void printLog() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<AttributeType, Long> entry : this.finalAttributeMap.entrySet()) {
            sb.append(entry.getKey());
            sb.append(':');
            sb.append(entry.getValue());
            sb.append('|');
        }
        System.out.println(sb.toString());
    }

}
