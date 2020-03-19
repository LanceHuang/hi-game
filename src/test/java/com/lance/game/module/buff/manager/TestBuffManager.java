package com.lance.game.module.buff.manager;

import com.lance.common.tool.util.TimeUtils;
import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.buff.model.BuffType;
import org.springframework.stereotype.Repository;

/**
 * @author Lance
 */
@Repository("testBuffManager")
public class TestBuffManager implements IBuffManager {

    @Override
    public BuffConfig getBuffConfig(int buffId) {
        BuffConfig buffConfig = new BuffConfig();
        buffConfig.setId(buffId);
        buffConfig.setType(BuffType.ATTRIBUTE.getType());
        buffConfig.setDuration(10 * TimeUtils.ONE_SECOND);
        return buffConfig;
    }
}
