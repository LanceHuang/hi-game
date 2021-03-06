package com.lance.game.demo.module.item.factory;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.model.AbstractItem;
import com.lance.game.demo.module.item.model.Equipment;

/**
 * 装备工厂
 *
 * @author Lance
 */
public class EquipmentFactory implements IItemFactory {

    @Override
    public AbstractItem create(ItemConfig config) {
        Equipment item = new Equipment();
        item.init(config);
        // todo 生成属性
        return item;
    }
}
