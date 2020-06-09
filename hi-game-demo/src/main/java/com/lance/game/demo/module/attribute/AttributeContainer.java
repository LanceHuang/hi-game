package com.lance.game.demo.module.attribute;

import com.lance.game.demo.module.attribute.formula.AbstractAttributeFormula;
import com.lance.game.demo.module.attribute.formula.IAttributeFormula;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性面板
 *
 * @author Lance
 */
public class AttributeContainer {

    /** 原始属性 */
    private Map<ModuleAttributeId, Map<AttributeType, Long>> rawAttributeMap = new HashMap<>();

    /** 最终属性 */
    private Map<AttributeType, Long> finalAttributeMap = new HashMap<>();

    /**
     * 添加模块属性
     *
     * @param mai          模块属性id
     * @param attributeMap 模块属性表
     */
    public void putAttributes(ModuleAttributeId mai, Map<AttributeType, Long> attributeMap) {
        if (mai == null || attributeMap == null) {
            return;
        }
        this.rawAttributeMap.put(mai, attributeMap);
    }

    /**
     * 计算属性
     */
    public void recompute() {
        // 1. flat
        Map<AttributeType, Long> flatAttributeMap = new HashMap<>();
        for (Map<AttributeType, Long> map : this.rawAttributeMap.values()) {
            for (Map.Entry<AttributeType, Long> entry : map.entrySet()) {
                AttributeType type = entry.getKey();
                Long value = entry.getValue();
                if (type == null || value == null) {
                    continue;
                }

                flatAttributeMap.merge(type, value, Long::sum);
            }
        }

        // 2. compute
        // f(x) = x                          formula(x) == null
        // f(x) = g(flatAttributeMap)   formula(x) != null
        Map<AttributeType, Long> tempAttributeMap = new HashMap<>();
        Map<AttributeType, IAttributeFormula> attributeFormulas = AbstractAttributeFormula.getAttributeFormulas();
        for (Map.Entry<AttributeType, Long> entry : flatAttributeMap.entrySet()) {
            AttributeType type = entry.getKey(); // 属性类型
            if (attributeFormulas.containsKey(type)) { // 需要计算的属性
                long value = attributeFormulas.get(type).calculate(flatAttributeMap);
                tempAttributeMap.put(type, value);
            } else {
                tempAttributeMap.put(type, entry.getValue());
            }
        }

        // 3. replace
        this.finalAttributeMap = tempAttributeMap;
    }

    /**
     * 清空属性
     */
    public void clear() {
        this.rawAttributeMap = new HashMap<>();
        this.finalAttributeMap = new HashMap<>();
    }

    /**
     * 打印最终属性
     */
    public void logAttributes() {
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
