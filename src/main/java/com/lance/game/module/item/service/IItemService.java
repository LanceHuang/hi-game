package com.lance.game.module.item.service;

import com.lance.game.module.player.model.Player;

/**
 * 道具
 *
 * @author Lance
 * @since 2019/8/30 16:05
 */
public interface IItemService {

    /**
     * 使用道具
     */
    void useItem(Player player, long id);

    // todo 批量使用道具？
}
