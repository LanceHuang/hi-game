package com.lance.game.module.buff.model;

/**
 * buff类型
 *
 * @author Lance
 */
public enum BuffType {

    /** 属性加成 */
    ATTRIBUTE(1) {
        @Override
        public AbstractBuff create() {
            return new AttributeBuff();
        }
    };

    private int type;

    BuffType(int type) {
        this.type = type;
    }

    public static BuffType typeOf(int type) {
        for (BuffType buffType : values()) {
            if (buffType.type == type) {
                return buffType;
            }
        }
        return null;
    }

    public abstract AbstractBuff create();

    public int getType() {
        return type;
    }
}
