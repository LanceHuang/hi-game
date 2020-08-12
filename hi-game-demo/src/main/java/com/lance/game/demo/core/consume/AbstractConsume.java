package com.lance.game.demo.core.consume;

import com.lance.game.demo.core.condition.AbstractCondition;
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
