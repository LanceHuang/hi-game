package com.lance.game.module.player.manager;

import com.lance.game.module.player.model.Player;

/**
 * @author Lance
 */
public interface IPlayerManager {

    /**
     * 添加或更新玩家数据
     */
    void savePlayer(Player player);

    /**
     * 查询玩家数据
     *
     * @param id 唯一标识
     */
    Player getPlayer(long id);
}
