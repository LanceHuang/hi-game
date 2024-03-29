package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.ModuleId;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

import static com.lance.game.demo.module.attribute.constant.AttributeConstant.MAX_RATE;

/**
 * 攻击力计算器
 *
 * @author Lance
 * @since 2021/8/31
 */
public class AtkComputer extends AttributeComputer {

    @Override
    public long compute(Map<ModuleId, Map<AttributeType, Long>> moduleAttrs,
                        Map<AttributeType, Long> originAttrs,
                        AttributeType type,
                        long value) {

        double atkRate = MapUtils.getLongValue(originAttrs, AttributeType.ATK_RATE, 0L);
        double atkValue = value * (1 + atkRate / MAX_RATE);
        return (long) atkValue;
    }
}
