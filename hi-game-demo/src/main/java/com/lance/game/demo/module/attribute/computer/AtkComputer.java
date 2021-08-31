package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.ModuleAttributeId;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 攻击力计算器
 *
 * @author Lance
 * @since 2021/8/31
 */
public class AtkComputer extends AttributeComputer {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.ATK;
    }

    @Override
    public long compute(Map<ModuleAttributeId, Map<AttributeType, Long>> moduleAttributeMap, Map<AttributeType, Long> attributeMap) {
        double value = MapUtils.getLongValue(attributeMap, AttributeType.ATK, 0L);
        double rate = MapUtils.getLongValue(attributeMap, AttributeType.ATK_RATE, 0L);
        value = value * (10000 + rate) / 10000;
        return (long) value;
    }
}
