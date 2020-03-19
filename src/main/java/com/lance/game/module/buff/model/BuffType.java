package com.lance.game.module.buff.model;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.buff.factory.AttributeBuffFactory;
import com.lance.game.module.buff.factory.IBuffFactory;

/**
 * buff类型
 *
 * @author Lance
 */
public enum BuffType {

    /** 属性加成 */
    ATTRIBUTE(1, new AttributeBuffFactory());

    private int type;

    private IBuffFactory factory;

    BuffType(int type, IBuffFactory factory) {
        this.type = type;
        this.factory = factory;
    }

    public static BuffType typeOf(int type) {
        for (BuffType buffType : values()) {
            if (buffType.type == type) {
                return buffType;
            }
        }
        return null;
    }

    public AbstractBuff create(BuffConfig buffConfig) {
        return this.factory.create(buffConfig);
    }

    public int getType() {
        return type;
    }

}
