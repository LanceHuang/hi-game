package com.lance.game.module.buff.manager;

import com.lance.config.annotation.Inject;
import com.lance.config.model.ConfigStorage;
import com.lance.game.module.buff.config.BuffConfig;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * buff持久层
 *
 * @author Lance
 */
@Repository
public class BuffManager implements IBuffManager {

    @Inject
    private ConfigStorage<BuffConfig> buffStorage;

    @Override
    public BuffConfig getBuffConfig(int buffId) {
        return buffStorage.get(buffId);
    }

    @Override
    public Collection<BuffConfig> getAllBuffConfig() {
        return buffStorage.getAll();
    }
}
