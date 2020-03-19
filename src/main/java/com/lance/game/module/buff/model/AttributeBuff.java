package com.lance.game.module.buff.model;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.util.LoggerUtil;

/**
 * 属性buff
 *
 * @author Lance
 */
public class AttributeBuff extends AbstractBuff {

    public AttributeBuff(BuffConfig buffConfig) {
        super(buffConfig);
    }

    @Override
    public void activate() {
        LoggerUtil.info("玩家{}添加属性buff={}", this.owner.getAccount(), this.id);
    }

    @Override
    public void deactivate() {
        LoggerUtil.info("玩家{}移除属性buff={}", this.owner.getAccount(), this.id);
    }
}
