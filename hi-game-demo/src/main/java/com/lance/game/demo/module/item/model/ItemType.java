package com.lance.game.demo.module.item.model;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.factory.MedicineFactory;
import com.lance.game.demo.module.item.factory.SimpleItemFactory;
import com.lance.game.demo.module.item.factory.EquipmentFactory;
import com.lance.game.demo.module.item.factory.IItemFactory;

/**
 * 道具类型
 *
 * @author Lance
 */
public enum ItemType {

    /** 普通道具 */
    SIMPLE_ITEM(1, new SimpleItemFactory()),
    /** 药水 */
    MEDICINE(2, new MedicineFactory()),
    /** 装备 */
    EQUIPMENT(3, new EquipmentFactory()),
    ;

    private int type;

    private IItemFactory factory;

    ItemType(int type, IItemFactory factory) {
        this.type = type;
        this.factory = factory;
    }

    public AbstractItem create(ItemConfig config) {
        return this.factory.create(config);
    }

    public static ItemType typeOf(int type) {
        for (ItemType itemType : values()) {
            if (itemType.type == type) {
                return itemType;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }
}
