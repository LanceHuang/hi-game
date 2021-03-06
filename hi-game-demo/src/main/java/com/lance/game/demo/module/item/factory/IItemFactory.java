package com.lance.game.demo.module.item.factory;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.model.AbstractItem;

/**
 * 道具工厂
 *
 * @author Lance
 */
public interface IItemFactory {

    /**
     * 创建道具
     *
     * @param config 道具配置
     */
    AbstractItem create(ItemConfig config);
}
