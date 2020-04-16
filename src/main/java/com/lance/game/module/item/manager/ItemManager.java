package com.lance.game.module.item.manager;

import com.lance.game.module.item.config.ItemConfig;
import org.springframework.stereotype.Repository;

/**
 * @author Lance
 */
@Repository
public class ItemManager implements IItemManager {

    @Override
    public ItemConfig getItemConfig(int id) {
        return null;
    }
}
