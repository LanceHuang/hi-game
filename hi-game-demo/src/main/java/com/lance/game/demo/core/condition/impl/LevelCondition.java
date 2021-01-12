package com.lance.game.demo.core.condition.impl;

import com.lance.game.demo.core.condition.AbstractCondition;
import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.util.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

/**
 * 等级大于或等于X
 *
 * @author Lance
 */
public class LevelCondition extends AbstractCondition {

    /** 等级 */
    private int level;

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
        if (player.getLevel() < this.level) {
            verifyResult.fail(I18nId.LEVEL_NOT_ENOUGH);
        }
    }

    @Override
    public void parse(String value) {
        this.level = Integer.parseInt(value);
    }

}
