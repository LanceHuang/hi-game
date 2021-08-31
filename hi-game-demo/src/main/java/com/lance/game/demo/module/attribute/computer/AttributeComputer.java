package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.ModuleAttributeId;

import java.util.Map;

/**
 * 属性计算器
 *
 * @author Lance
 * @since 2021/8/31
 */
public abstract class AttributeComputer {

    /**
     * 获取计算类型
     *
     * @return 计算类型
     */
    public abstract AttributeType getAttributeType();

    /**
     * 计算属性值
     *
     * @param moduleAttributeMap 模块属性
     * @param attributeMap       中间属性
     * @return 属性值
     */
    public abstract long compute(Map<ModuleAttributeId, Map<AttributeType, Long>> moduleAttributeMap, Map<AttributeType, Long> attributeMap);
}
