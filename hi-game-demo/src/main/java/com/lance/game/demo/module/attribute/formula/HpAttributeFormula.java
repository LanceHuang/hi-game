package com.lance.game.demo.module.attribute.formula;

import com.lance.game.demo.module.attribute.AttributeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 生命属性公式
 *
 * @author Lance
 */
@Component
public class HpAttributeFormula extends AbstractAttributeFormula {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.HP;
    }

    @Override
    public long calculate(Map<AttributeType, Long> flatAttributeMap) {
        long hp = flatAttributeMap.getOrDefault(AttributeType.HP, 0L);
        long hpRate = flatAttributeMap.getOrDefault(AttributeType.HP_RATE, 0L);
        return Math.round(hp * (1 + hpRate / 10000.0));
    }
}
