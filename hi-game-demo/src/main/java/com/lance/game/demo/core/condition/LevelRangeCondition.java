package com.lance.game.demo.core.condition;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.core.model.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

/**
 * 等级大于等于X级，小于等于Y级
 *
 * @author Lance
 */
public class LevelRangeCondition extends AbstractCondition {

    private int fromLevel;
    private int toLevel;

    @Override
    public void parse(String value) {
        String[] split = value.split("_");
        this.fromLevel = Integer.parseInt(split[0]);
        this.toLevel = Integer.parseInt(split[1]);
    }

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
        if (this.fromLevel > player.getLevel() || player.getLevel() > this.toLevel) {
            verifyResult.fail(I18nId.LEVEL_NOT_MATCH);
        }
    }
}
