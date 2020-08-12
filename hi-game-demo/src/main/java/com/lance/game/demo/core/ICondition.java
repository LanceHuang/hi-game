package com.lance.game.demo.core;

import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.player.model.Player;

/**
 * 条件
 *
 * @author Lance
 */
public interface ICondition {

    /**
     * 条件校验
     *
     * @return 校验结果，false 校验失败
     */
    boolean verify(Player player);

    /**
     * 条件校验
     *
     * @throws GameException 校验失败
     */
    void verifyThrow(Player player) throws GameException;
}
