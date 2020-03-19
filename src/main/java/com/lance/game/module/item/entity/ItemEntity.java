package com.lance.game.module.item.entity;

/**
 * @author Lance
 * @since 2019/7/7 16:41
 */
public class ItemEntity {


    private long id;
    private int  itemId;
    //todo
//    private ItemType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
