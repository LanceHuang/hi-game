package com.lance.game.module.storage.service;

import com.lance.game.module.item.model.AbstractItem;
import com.lance.game.module.item.model.ItemType;
import com.lance.game.module.player.model.Player;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Lance
 * @since 2019/8/20 15:49
 */
@Service
public class StorageService implements IStorageService {

    @Override
    public AbstractItem createItem(int itemId) {
        // todo 临时硬编码，因为还没有创建数据库
//        AbstractItem item = ItemType.MEDICINE.create();
//        item.setId(System.currentTimeMillis());
//        item.setItemId(itemId);
//        item.setType(ItemType.MEDICINE);
//        return item;
        return null;
    }

    @Override
    public boolean addItem(Player player, AbstractItem item) {
        if (null == player || null == item) {
            return false;
        }
        return player.getItemStorage().addItem(item);
    }

    @Override
    public AbstractItem getItemById(Player player, long id) {
        if (null == player || null == player.getItemStorage()) {
            return null;
        }
        return player.getItemStorage().getItemById(id);
    }

    @Override
    public List<AbstractItem> getItemsByType(Player player, ItemType type) {
        if (null == player || null == player.getItemStorage()) {
            return Collections.emptyList();
        }
        return player.getItemStorage().getItemsByType(type);
    }

    @Override
    public boolean removeItemById(Player player, long id) {
        if (null == player) {
            return false;
        }
        return player.getItemStorage().removeItemById(id);
    }
}
