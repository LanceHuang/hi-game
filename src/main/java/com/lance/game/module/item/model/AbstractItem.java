package com.lance.game.module.item.model;

import lombok.Data;

/**
 * 道具
 *
 * @author Lance
 * @since 2019/7/4 20:32
 */
@Data
public abstract class AbstractItem {

    private long     id;
    private int      itemId;
    private ItemType type;

}
