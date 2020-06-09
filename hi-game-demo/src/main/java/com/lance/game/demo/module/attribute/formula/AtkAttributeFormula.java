package com.lance.game.demo.module.attribute.formula;

import com.lance.game.demo.module.attribute.AttributeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 攻击属性公式
 *
 * @author Lance
 */
@Component
public class AtkAttributeFormula extends AbstractAttributeFormula {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.ATK;
    }

    @Override
    public long calculate(Map<AttributeType, Long> flatAttributeMap) {
        long atk = flatAttributeMap.getOrDefault(AttributeType.ATK, 0L);
        long atkRate = flatAttributeMap.getOrDefault(AttributeType.ATK_RATE, 0L);
        long atkFix = flatAttributeMap.getOrDefault(AttributeType.ATK_FIX, 0L);
        return Math.round(atk * (1 + atkRate / 10000.0)) + atkFix;
    }
}
