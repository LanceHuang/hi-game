package com.lance.game.demo.condition;

import com.lance.game.demo.util.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

/**
 * 永真条件
 *
 * @author Lance
 */
public class TrueCondition extends AbstractCondition {

    @Override
    public void parse(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
    }
}
