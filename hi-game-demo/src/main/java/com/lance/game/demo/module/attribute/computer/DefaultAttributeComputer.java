package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.ModuleId;

import java.util.Map;

/**
 * 默认属性计算器
 *
 * @author Lance
 * @since 2021/9/2
 */
public class DefaultAttributeComputer extends AttributeComputer {

    @Override
    public long compute(Map<ModuleId, Map<AttributeType, Long>> moduleAttrs,
                        Map<AttributeType, Long> originAttrs,
                        AttributeType type,
                        long value) {
        return value;
    }

    public static DefaultAttributeComputer getInstance() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {
        private static final DefaultAttributeComputer INSTANCE = new DefaultAttributeComputer();
    }

}
