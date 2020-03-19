package com.lance.game.module.buff.factory;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.buff.model.AbstractBuff;

/**
 * @author Lance
 */
public interface IBuffFactory {

    AbstractBuff create(BuffConfig buffConfig);
}
