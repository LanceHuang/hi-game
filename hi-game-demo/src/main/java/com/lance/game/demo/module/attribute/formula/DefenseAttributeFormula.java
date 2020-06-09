package com.lance.game.demo.module.attribute.formula;

import com.lance.game.demo.module.attribute.AttributeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 防御属性公式
 *
 * @author Lance
 */
@Component
public class DefenseAttributeFormula extends AbstractAttributeFormula {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.DEFENSE;
    }

    @Override
    public long calculate(Map<AttributeType, Long> calculateAttributeMap) {
        long defense = calculateAttributeMap.getOrDefault(AttributeType.DEFENSE, 0L);
        long defenseRate = calculateAttributeMap.getOrDefault(AttributeType.DEFENSE_RATE, 0L);
        return Math.round(defense * (1 + defenseRate / 10000.0));
    }
}
