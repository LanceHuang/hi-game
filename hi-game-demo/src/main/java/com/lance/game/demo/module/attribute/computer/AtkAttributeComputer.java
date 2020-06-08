package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.AttributeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 攻击属性计算器
 *
 * @author Lance
 */
@Component
public class AtkAttributeComputer extends AbstractAttributeComputer {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.ATK;
    }

    @Override
    public long compute(Map<AttributeType, Long> computeAttributeMap) {
        long atk = computeAttributeMap.getOrDefault(AttributeType.ATK, 0L);
        long atkRate = computeAttributeMap.getOrDefault(AttributeType.ATK_RATE, 0L);
        long atkFix = computeAttributeMap.getOrDefault(AttributeType.ATK_FIX, 0L);
        return Math.round(atk * (1 + atkRate / 10000.0)) + atkFix;
    }
}
