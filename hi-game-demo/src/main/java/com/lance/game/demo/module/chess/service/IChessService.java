package com.lance.game.demo.module.chess.service;

import com.lance.game.demo.module.player.model.Player;

/**
 * 自走棋
 *
 * @author Lance
 */
public interface IChessService {

    /**
     * 开始匹配
     */
    void match(Player player);

    /**
     * 购买棋子
     *
     * @param id 棋子id
     */
    void buy(Player player, int id);

    /**
     * 准备战斗
     */
    void ready(Player player);
}
