package com.lance.game.module.player.service;

import com.lance.game.module.player.model.Player;

/**
 * 玩家
 *
 * @author Lance
 * @since 2019/9/2 15:42
 */
public interface IPlayerService {

    Player getPlayer(long id);

}
