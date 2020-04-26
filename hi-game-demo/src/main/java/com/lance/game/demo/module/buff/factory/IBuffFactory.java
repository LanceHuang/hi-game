package com.lance.game.demo.module.buff.factory;

import com.lance.game.demo.module.buff.config.BuffConfig;
import com.lance.game.demo.module.buff.model.AbstractBuff;

/**
 * @author Lance
 */
public interface IBuffFactory {

    AbstractBuff create(BuffConfig buffConfig);
}
