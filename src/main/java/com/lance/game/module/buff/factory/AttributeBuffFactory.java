package com.lance.game.module.buff.factory;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.buff.model.AbstractBuff;
import com.lance.game.module.buff.model.AttributeBuff;

/**
 * @author Lance
 */
public class AttributeBuffFactory implements IBuffFactory {

    @Override
    public AbstractBuff create(BuffConfig buffConfig) {
        return new AttributeBuff(buffConfig);
    }
}
