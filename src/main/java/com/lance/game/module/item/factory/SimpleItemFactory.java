package com.lance.game.module.item.factory;

import com.lance.game.module.item.config.ItemConfig;
import com.lance.game.module.item.model.AbstractItem;
import com.lance.game.module.item.model.SimpleItem;

/**
 * 普通道具工厂
 *
 * @author Lance
 */
public class SimpleItemFactory implements IItemFactory {

    @Override
    public AbstractItem create(ItemConfig config) {
        SimpleItem item = new SimpleItem();
        item.init(config);
        return item;
    }
}
