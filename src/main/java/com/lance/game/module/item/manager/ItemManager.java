package com.lance.game.module.item.manager;

import com.lance.common.util.MongoUtils;
import com.lance.game.module.item.config.ItemConfig;
import com.lance.game.module.item.handler.ItemDocumentHandler;
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
