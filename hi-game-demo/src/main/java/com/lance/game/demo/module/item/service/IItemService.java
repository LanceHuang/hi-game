package com.lance.game.demo.module.item.service;

import com.lance.game.demo.module.item.model.AbstractItem;

import java.util.List;

/**
 * 道具
 *
 * @author Lance
 */
public interface IItemService {

    /**
     * 创建道具
     *
     * @param id 道具id
     */
    AbstractItem createItem(int id);

    /**
     * 创建道具
     *
     * @param id  道具id
     * @param num 道具数量
     */
    List<AbstractItem> createItem(int id, int num);

}
