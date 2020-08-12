package com.lance.game.demo.core.consume;

import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.player.model.Player;

/**
 * 消耗材料
 *
 * @author Lance
 */
public interface IConsume {

    /**
     * 校验材料
     */
    boolean verify(Player player);

    /**
     * 校验材料
     */
    void verifyThrow(Player player) throws GameException;

    /**
     * 校验并消耗材料
     */
    void verifyAndConsume(Player player) throws GameException;

    /**
     * 消耗材料
     */
    void consume(Player player);
}
