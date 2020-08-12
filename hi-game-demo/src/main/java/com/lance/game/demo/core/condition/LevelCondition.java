package com.lance.game.demo.core.condition;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.module.player.model.Player;

/**
 * 等级大于等于X级
 *
 * @author Lance
 */
public class LevelCondition extends AbstractCondition {

    private int level;

    @Override
    public void parse(String value) {
        this.level = Integer.parseInt(value);
    }

    @Override
    public boolean verify(Player player) {
        return player.getLevel() >= this.level;
    }

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
        if (player.getLevel() < this.level) {
            verifyResult.fail(I18nId.LEVEL_NOT_ENOUGH);
        }
    }
}
