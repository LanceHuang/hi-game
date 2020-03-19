package com.lance.game.module.buff.manager;

import com.lance.game.module.buff.config.BuffConfig;

import java.util.Collection;

/**
 * @author Lance
 */
public interface IBuffManager {

    BuffConfig getBuffConfig(int buffId);

    Collection<BuffConfig> getAllBuffConfig();
}
