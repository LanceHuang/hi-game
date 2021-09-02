package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.ModuleId;

import java.util.Map;

/**
 * 属性计算器
 *
 * @author Lance
 * @since 2021/8/31
 */
public abstract class AttributeComputer {

    /**
     * 计算属性值
     *
     * @param moduleAttrs 模块属性
     * @param originAttrs 白值属性
     * @param type        属性类型
     * @param value       属性值
     * @return 新属性值
     */
    public abstract long compute(Map<ModuleId, Map<AttributeType, Long>> moduleAttrs,
                                 Map<AttributeType, Long> originAttrs,
                                 AttributeType type,
                                 long value);
}
