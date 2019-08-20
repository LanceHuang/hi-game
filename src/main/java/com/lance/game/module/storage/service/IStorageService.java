package com.lance.game.module.storage.service;

import com.lance.game.module.item.model.AbstractItem;
import com.lance.game.module.item.model.ItemType;
import com.lance.game.module.player.model.Player;

import java.util.List;

/**
 * 背包操作类
 *
 * @author Lance
 * @since 2019/8/20 15:43
 */
public interface IStorageService {

    /**
     * 添加道具
     *
     * @return {@code true} 添加成功
     */
    boolean addItem(Player player, AbstractItem item);

    /**
     * 根据道具id查询道具
     *
     * @param id 道具id
     */
    AbstractItem getItemById(Player player, long id);

    /**
     * 根据道具类型查询道具
     *
     * @param type 道具类型
     */
    List<AbstractItem> getItemsByType(Player player, ItemType type);

    /**
     * 根据道具id删除道具
     *
     * @param id 道具id
     * @return {@code true} 删除成功
     */
    boolean removeItemById(Player player, long id);
}