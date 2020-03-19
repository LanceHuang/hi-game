package com.lance.game.module.attribute.model;

/**
 * @author Lance
 * @since 2019/7/4 20:40
 */
public class Attribute {

    private AttributeType type;
    private long          value;

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
