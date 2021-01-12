package com.lance.game.demo.module.attribute.effect;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.AttributeContainer;

import java.util.Map;

/**
 * 属性效果计算器
 *
 * @author Lance
 * @since 2021/1/12
 */
public abstract class AttributeEffect {

    /**
     * 计算属性效果
     *
     * @param attributeContainer 属性容器
     * @param attributeType      属性类型
     * @param value              属性值
     * @return 属性效果
     */
    public abstract Map<AttributeType, Long> compute(AttributeContainer attributeContainer, AttributeType attributeType, Long value);
}
