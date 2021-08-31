package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.operate.AttributeOperate;
import com.lance.game.demo.module.attribute.operate.BasicAttributeOperate;

/**
 * 属性运算类型
 *
 * @author Lance
 * @since 2021/1/12
 */
public enum AttributeOperateType {

    /** 基本运算 */
    BASIC(new BasicAttributeOperate());

    private final AttributeOperate attributeOperate;

    AttributeOperateType(AttributeOperate attributeOperate) {
        this.attributeOperate = attributeOperate;
    }

    public AttributeOperate getAttributeOperate() {
        return attributeOperate;
    }
}
