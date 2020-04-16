package com.lance.game.module.item.model;

import com.lance.game.module.item.config.ItemConfig;

/**
 * 道具
 *
 * @author Lance
 */
public abstract class AbstractItem {

    /** 唯一标识 */
    private long objectId;

    /** 道具id */
    private int id;
    /** 道具类型 */
    private int type;
    /** 道具数量 */
    private int num;

    public void init(ItemConfig config) {
        this.id = config.getId();
        this.type = config.getType();
        this.num = 1;
    }

    /**
     * 判断道具能否使用
     */
    // todo
    public boolean isUsable() {
        return false;
    }

    //==================================== Getter/Setter ============================

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
