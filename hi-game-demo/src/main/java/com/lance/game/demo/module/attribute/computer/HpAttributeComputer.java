package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.AttributeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 生命属性计算器
 *
 * @author Lance
 */
@Component
public class HpAttributeComputer extends AbstractAttributeComputer {

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.HP;
    }

    @Override
    public long compute(Map<AttributeType, Long> computeAttributeMap) {
        long hp = computeAttributeMap.getOrDefault(AttributeType.HP, 0L);
        long hpRate = computeAttributeMap.getOrDefault(AttributeType.HP_RATE, 0L);
        return Math.round(hp * (1 + hpRate / 10000.0));
    }
}
