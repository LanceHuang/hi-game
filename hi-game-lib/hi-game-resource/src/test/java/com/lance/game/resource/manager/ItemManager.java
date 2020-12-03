package com.lance.game.resource.manager;

import com.lance.game.resource.GameResourceStorage;
import com.lance.game.resource.annotation.GameResourceInject;
import com.lance.game.resource.resource.ItemResource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Lance
 * @since 2020/12/2
 */
@Component
public class ItemManager {

    @GameResourceInject
    private GameResourceStorage<Integer, ItemResource> itemResourceManager;

    /**
     * 获取道具配置
     *
     * @param id 道具id
     */
    public ItemResource getItemResource(int id) {
        return itemResourceManager.get(id);
    }

    /**
     * 获取所有道具配置
     */
    public Collection<ItemResource> getAllItemResource() {
        return itemResourceManager.getAll();
    }
}
