package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.operator.AttributeOperator;
import com.lance.game.demo.module.attribute.operator.DefaultAttributeOperator;

/**
 * 属性运算类型
 *
 * @author Lance
 * @since 2021/1/12
 */
public enum AttributeOperateType {

    /** 基本运算 */
    BASIC(new DefaultAttributeOperator());

    private final AttributeOperator attributeOperator;

    AttributeOperateType(AttributeOperator attributeOperator) {
        this.attributeOperator = attributeOperator;
    }

    public AttributeOperator getAttributeOperate() {
        return attributeOperator;
    }
}
