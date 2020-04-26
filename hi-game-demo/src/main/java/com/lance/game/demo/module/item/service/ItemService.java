package com.lance.game.demo.module.item.service;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.manager.IItemManager;
import com.lance.game.demo.module.item.model.AbstractItem;
import com.lance.game.demo.module.item.model.ItemType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lance
 */
@Service
public class ItemService implements IItemService {

    @Resource
    private IItemManager itemManager;

    @Override
    public AbstractItem createItem(int id) {
        ItemConfig itemConfig = itemManager.getItemConfig(id);
        if (itemConfig == null) {
            return null;
        }

        ItemType itemType = ItemType.typeOf(itemConfig.getType());
        if (itemType == null) {
            return null;
        }

        return itemType.create(itemConfig);
    }

    @Override
    public List<AbstractItem> createItem(int id, int num) {
        // todo
        return null;
    }

}
