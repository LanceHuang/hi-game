package com.lance.game.module.buff.service;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.buff.manager.BuffManager;
import com.lance.game.module.buff.model.AbstractBuff;
import com.lance.game.module.buff.model.BuffContainer;
import com.lance.game.module.buff.model.BuffType;
import com.lance.game.module.player.model.Player;
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

    @Override
    public void createAndAddBuff(Player player, int id) {
        if (player == null) {
            LoggerUtil.error("player对象不能为空");
            return;
        }

        BuffContainer buffContainer = player.getBuffContainer();
        if (buffContainer == null) {
            LoggerUtil.error("buffContainer为空，添加buff失败，account:{},buffId:{}", player.getAccount(), id);
            return;
        }

        buffContainer.add(createBuff(id));
    }

    @Override
    public void removeBuff(Player player, int id) {
        if (player == null) {
            LoggerUtil.error("player对象不能为空");
            return;
        }

        BuffContainer buffContainer = player.getBuffContainer();
        if (buffContainer == null) {
            LoggerUtil.error("buffContainer为空，移除buff失败，account:{},buffId:{}", player.getAccount(), id);
            return;
        }

        buffContainer.remove(id);
    }

    @Override
    public boolean containsBuff(Player player, int id) {
        if (player == null) {
            LoggerUtil.error("player对象不能为空");
            return false;
        }

        BuffContainer buffContainer = player.getBuffContainer();
        if (buffContainer == null) {
            return false;
        }

        return buffContainer.contains(id);
    }
}
