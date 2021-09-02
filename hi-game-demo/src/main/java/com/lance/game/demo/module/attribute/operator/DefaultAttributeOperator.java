package com.lance.game.demo.module.attribute.operator;

/**
 * 基本运算
 *
 * @author Lance
 * @since 2021/1/12
 */
public class DefaultAttributeOperator extends AttributeOperator {

    @Override
    public long add(long value, long addValue) {
        return value + addValue;
    }

    @Override
    public long reduce(long value, long reduceValue) {
        return value - reduceValue;
    }

    public static DefaultAttributeOperator getInstance() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {
        private static final DefaultAttributeOperator INSTANCE = new DefaultAttributeOperator();
    }
}
