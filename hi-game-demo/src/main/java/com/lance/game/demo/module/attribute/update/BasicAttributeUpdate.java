package com.lance.game.demo.module.attribute.update;

/**
 * 基本属性更新操作
 *
 * @author Lance
 * @since 2021/1/12
 */
public class BasicAttributeUpdate extends AttributeUpdate {

    @Override
    public long add(long value, long addValue) {
        return value + addValue;
    }

    @Override
    public long minus(long value, long minusValue) {
        return value - minusValue;
    }
}
