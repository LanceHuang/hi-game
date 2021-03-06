package com.lance.game.demo.module.item.factory;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.model.AbstractItem;
import com.lance.game.demo.module.item.model.Medicine;

/**
 * 药水工厂
 *
 * @author Lance
 */
public class MedicineFactory implements IItemFactory {

    @Override
    public AbstractItem create(ItemConfig config) {
        Medicine item = new Medicine();
        item.init(config);
        return item;
    }
}
