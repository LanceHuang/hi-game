package com.lance.game.demo.module.attribute.formula;

import com.lance.game.demo.module.attribute.AttributeType;

import java.util.Map;

/**
 * 属性公式
 *
 * @author Lance
 */
public interface IAttributeFormula {

    /**
     * 计算的属性类型
     */
    AttributeType getAttributeType();

    /**
     * 计算属性值
     *
     * @param flatAttributeMap 属性表
     */
    long calculate(Map<AttributeType, Long> flatAttributeMap);
}
