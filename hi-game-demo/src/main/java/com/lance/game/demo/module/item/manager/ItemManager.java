package com.lance.game.demo.module.item.manager;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.handler.ItemDocumentHandler;
import com.lance.game.demo.util.MongoUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Lance
 */
@Repository
public class ItemManager implements IItemManager {

    private static final String DATABASE_NAME   = "game";
    private static final String COLLECTION_NAME = "item";

    @Override
    public ItemConfig getItemConfig(int id) {
        return MongoUtils.findOne(DATABASE_NAME, COLLECTION_NAME, "{id:" + id + "}", new ItemDocumentHandler());
    }
}
