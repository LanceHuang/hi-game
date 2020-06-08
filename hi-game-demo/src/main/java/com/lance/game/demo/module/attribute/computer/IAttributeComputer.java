package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.AttributeType;

import java.util.Map;

/**
 * 属性计算器
 *
 * @author Lance
 */
public interface IAttributeComputer {

    AttributeType getAttributeType();

    long compute(Map<AttributeType, Long> computeAttributeMap);
}
