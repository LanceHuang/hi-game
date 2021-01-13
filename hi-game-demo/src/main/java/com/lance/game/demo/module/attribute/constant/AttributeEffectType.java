package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.effect.AttributeEffect;
import com.lance.game.demo.module.attribute.effect.ModuleRatioAttributeEffect;
import com.lance.game.demo.module.attribute.effect.RatioAttributeEffect;

/**
 * 属性效果类型
 *
 * @author Lance
 * @since 2021/1/12
 */
public enum AttributeEffectType {

    /** 按比例增加属性 */
    RATIO(new RatioAttributeEffect()),
    /** 按比例增加模块属性 */
    MODULE_RATIO(new ModuleRatioAttributeEffect());

    private AttributeEffect attributeEffect;

    AttributeEffectType(AttributeEffect attributeEffect) {
        this.attributeEffect = attributeEffect;
    }

    public AttributeEffect getAttributeEffect() {
        return attributeEffect;
    }
}
