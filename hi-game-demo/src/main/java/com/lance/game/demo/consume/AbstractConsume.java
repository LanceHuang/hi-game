package com.lance.game.demo.consume;

import com.lance.game.demo.condition.AbstractCondition;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.player.model.Player;

/**
 * @author Lance
 */
public abstract class AbstractConsume extends AbstractCondition implements IConsume {

    @Override
    public void verifyAndConsume(Player player) throws GameException {
        verifyThrow(player);
        consume(player);
    }
}
