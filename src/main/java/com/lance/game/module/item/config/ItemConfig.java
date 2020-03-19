package com.lance.game.module.item.config;

import com.lance.game.module.item.model.ItemType;

/**
 * @author Lance
 * @since 2019/7/4 20:35
 */
public class ItemConfig {

    private int      id;
    private String   name;
    private ItemType type;

    // todo 属性

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
