package com.lance.game.demo.module.attribute.operate;

/**
 * 基本运算
 *
 * @author Lance
 * @since 2021/1/12
 */
public class BasicAttributeOperate extends AttributeOperate {

    @Override
    public long add(long value, long addValue) {
        return value + addValue;
    }

    @Override
    public long reduce(long value, long reduceValue) {
        return value - reduceValue;
    }
}
