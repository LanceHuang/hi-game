package com.lance.game.module.buff.service;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.buff.manager.BuffManager;
import com.lance.game.module.buff.model.AbstractBuff;
import com.lance.game.module.buff.model.BuffType;
import com.lance.game.util.LoggerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class BuffService implements IBuffService {

    @Resource
    private BuffManager buffManager;

    @Override
    public AbstractBuff createBuff(int id) {
        BuffConfig buffConfig = buffManager.getBuffConfig(id);
        if (buffConfig == null) {
            LoggerUtil.error("没有相应的buff配置，id:{}", id);
            return null;
        }

        BuffType buffType = BuffType.typeOf(buffConfig.getType());
        if (buffType == null) {
            LoggerUtil.error("没有相应的buff类型，id:{},type:{}", id, buffConfig.getType());
            return null;
        }

        AbstractBuff buff = buffType.create();
        buff.init(buffConfig);
        return buff;
    }
}
