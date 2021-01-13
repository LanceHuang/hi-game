package com.lance.game.demo.module.attribute.update;

/**
 * 属性更新操作
 *
 * @author Lance
 * @since 2021/1/12
 */
public abstract class AttributeUpdate {

    /**
     * 更新属性
     *
     * @param value    初始化
     * @param newValue 新值
     * @return 最终值
     */
    public long update(long value, long newValue) { // todo 不确定要不要用
        long gap = newValue - value;
        if (gap > 0) {
            return add(value, gap);
        } else if (gap < 0) {
            return minus(value, gap);
        } else {
            return value;
        }
    }

    /**
     * 增加属性
     *
     * @param value    初始值
     * @param addValue 新增值
     */
    public abstract long add(long value, long addValue);

    /**
     * 减少属性
     *
     * @param value      初始值
     * @param minusValue 减少值
     */
    public abstract long minus(long value, long minusValue);
}
