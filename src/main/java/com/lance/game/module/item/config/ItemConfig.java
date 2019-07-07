package com.lance.game.module.item.config;

import com.lance.game.module.item.model.ItemType;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/7/4 20:35
 */
@Data
public class ItemConfig {

    private int      id;
    private String   name;
    private ItemType type;

}
