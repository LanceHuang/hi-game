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
public class BuffManager {

    @Inject
    private ConfigStorage<BuffConfig> buffStorage;

    public BuffConfig getBuffConfig(int buffId) {
        return buffStorage.get(buffId);
    }

    public Collection<BuffConfig> getBuffConfig() {
        return buffStorage.getAll();
    }
}
