package com.lance.game.demo.module.buff.manager;

import com.lance.config.annotation.Inject;
import com.lance.config.model.ConfigStorage;
import com.lance.game.demo.module.buff.config.BuffConfig;

/**
 * buff持久层
 *
 * @author Lance
 */
//@Repository
public class BuffManager implements IBuffManager {

    @Inject
    private ConfigStorage<BuffConfig> buffStorage;

    @Override
    public BuffConfig getBuffConfig(int buffId) {
        return buffStorage.get(buffId);
    }
}
