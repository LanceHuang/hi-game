package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.AttributeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 防御属性计算器
 *
 * @author Lance
 */
@Component
public class DefenseAttributeComputer extends AbstractAttributeComputer {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.DEFENSE;
    }

    @Override
    public long compute(Map<AttributeType, Long> computeAttributeMap) {
        long defense = computeAttributeMap.getOrDefault(AttributeType.DEFENSE, 0L);
        long defenseRate = computeAttributeMap.getOrDefault(AttributeType.DEFENSE_RATE, 0L);
        return Math.round(defense * (1 + defenseRate / 10000.0));
    }
}
