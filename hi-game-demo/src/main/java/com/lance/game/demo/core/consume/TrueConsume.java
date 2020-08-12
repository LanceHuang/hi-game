package com.lance.game.demo.core.consume;

import com.lance.game.demo.core.model.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

/**
 * 永真消耗
 *
 * @author Lance
 */
public class TrueConsume extends AbstractConsume {

    @Override
    public void parse(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
    }

    @Override
    public void consume(Player player) {
    }
}
