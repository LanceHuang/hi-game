package com.lance.game.demo.module.buff.factory;

import com.lance.game.demo.module.buff.config.BuffConfig;
import com.lance.game.demo.module.buff.model.AbstractBuff;
import com.lance.game.demo.module.buff.model.AttributeBuff;

/**
 * @author Lance
 */
public class AttributeBuffFactory implements IBuffFactory {

    @Override
    public AbstractBuff create(BuffConfig buffConfig) {
        return new AttributeBuff(buffConfig);
    }
}
