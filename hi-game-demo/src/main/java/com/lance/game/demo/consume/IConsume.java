package com.lance.game.demo.consume;

import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.player.model.Player;

/**
 * 消耗材料（不建议继承ICondition，并不会减少代码量，反而会增加代码复杂度，增大的耦合度）
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
