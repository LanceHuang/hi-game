package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.effect.AttributeEffect;

/**
 * 属性效果类型
 *
 * @author Lance
 * @since 2021/1/12
 */
public enum AttributeEffectType {

    ;

    private AttributeEffect attributeEffect;

    AttributeEffectType(AttributeEffect attributeEffect) {
        this.attributeEffect = attributeEffect;
    }

    public AttributeEffect getAttributeEffect() {
        return attributeEffect;
    }
}
