package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.update.AttributeUpdate;
import com.lance.game.demo.module.attribute.update.BasicAttributeUpdate;

/**
 * 属性更新类型
 *
 * @author Lance
 * @since 2021/1/12
 */
public enum AttributeUpdateType {

    /** 基本属性更新操作 */
    BASIC(new BasicAttributeUpdate());

    private AttributeUpdate attributeUpdate;

    AttributeUpdateType(AttributeUpdate attributeUpdate) {
        this.attributeUpdate = attributeUpdate;
    }

    public AttributeUpdate getAttributeUpdate() {
        return attributeUpdate;
    }
}
