package com.lance.game.resource.manager;

import com.lance.game.resource.GameResourceManager;
import com.lance.game.resource.resource.ItemResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author Lance
 * @since 2020/12/2
 */
@Component
public class ItemManager {

    @Resource
    private GameResourceManager gameResourceManager;

    /**
     * 获取道具配置
     *
     * @param id 道具id
     */
    public ItemResource getItemResource(int id) {
        return gameResourceManager.get(ItemResource.class, id);
    }

    /**
     * 获取所有道具配置
     */
    public Collection<ItemResource> getAllItemResource() {
        return gameResourceManager.getAll(ItemResource.class);
    }
}
