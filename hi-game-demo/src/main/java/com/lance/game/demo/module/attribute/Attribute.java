package com.lance.game.demo.module.attribute;

/**
 * 属性
 *
 * @author Lance
 */
public class Attribute {

    /** 属性类型 */
    private AttributeType type;

    /** 属性值 */
    private long value;

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
