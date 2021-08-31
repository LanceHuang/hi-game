package com.lance.game.demo.module.attribute.operate;

/**
 * 属性运算
 *
 * @author Lance
 * @since 2021/1/12
 */
public abstract class AttributeOperate {

    /**
     * 更新属性
     *
     * @param value      初始值
     * @param alterValue 更新值
     * @return 最终值
     */
    public long operate(long value, long alterValue) {
        if (alterValue > 0) {
            return add(value, alterValue);
        } else if (alterValue < 0) {
            return reduce(value, alterValue);
        } else {
            return value;
        }
    }

    /**
     * 增加属性
     *
     * @param value    初始值
     * @param addValue 新增值
     * @return 最终值
     */
    public abstract long add(long value, long addValue);

    /**
     * 减少属性
     *
     * @param value       初始值
     * @param reduceValue 减少值
     * @return 最终值
     */
    public abstract long reduce(long value, long reduceValue);
}
