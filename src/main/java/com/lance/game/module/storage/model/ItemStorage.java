package com.lance.game.module.storage.model;

import com.lance.game.module.item.model.AbstractItem;
import com.lance.game.module.item.model.ItemType;

import java.util.LinkedList;
import java.util.List;

/**
 * 道具背包（一个格子对应于一个道具，数组下标对应道具所在格子。发送数据给客户端时，转成Map，key为下标）
 *
 * @author Lance
 * @since 2019/8/20 15:44
 */
public class ItemStorage {

    private static final int CAPACITY_MAX = 100;

    private AbstractItem[] items;
    private int            capacity;
    private int            size;

    public ItemStorage() {
        this.items = new AbstractItem[CAPACITY_MAX];
        this.capacity = CAPACITY_MAX;
        this.size = 0;
    }

    /**
     * 添加道具
     *
     * @return {@code true} 添加成功
     */
    public boolean addItem(AbstractItem item) {
        if (null == item) {
            return false;
        }

        for (int i = 0; i < capacity; i++) {
            if (null == items[i]) {
                items[i] = item;
                size++;
                return true;
            }
        }
        return false;
    }

    /**
     * 根据道具id查询道具
     *
     * @param id 道具id
     */
    //    @JsonIgnore
    public AbstractItem getItemById(long id) {
        for (AbstractItem item : items) {
            if (null == item) {
                continue;
            }
            if (id == item.getId()) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据道具类型查询道具
     *
     * @param type 道具类型
     */
    //    @JsonIgnore
    public List<AbstractItem> getItemsByType(ItemType type) {
        List<AbstractItem> list = new LinkedList<>();
        for (AbstractItem item : items) {
            if (null == item) {
                continue;
            }
//            if (item.getType().equals(type)) {
//                list.add(item);
//            }
        }
        return list;
    }

    /**
     * 根据道具id删除道具
     *
     * @param id 道具id
     * @return {@code true} 删除成功
     */
    public boolean removeItemById(long id) {
        for (int i = 0; i < capacity; i++) {
            if (null == items[i]) {
                continue;
            }

            if (id == items[i].getId()) {
                items[i] = null;
                return true;
            }
        }
        return false;
    }
}
