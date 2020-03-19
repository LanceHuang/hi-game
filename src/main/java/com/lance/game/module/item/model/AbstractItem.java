package com.lance.game.module.item.model;

/**
 * 道具
 *
 * @author Lance
 * @since 2019/7/4 20:32
 */
public abstract class AbstractItem {

    private long     id;
    private int      itemId;
    private ItemType type;

    /**
     * 判断道具是否可以直接使用
     *
     * @return {@code true} 可直接使用
     */
    // todo @JsonIgnore
    public boolean isUsable() {
        return false;
    }

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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
