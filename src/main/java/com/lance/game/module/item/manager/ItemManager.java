package com.lance.game.module.item.manager;

import com.lance.common.util.MongoUtils;
import com.lance.common.util.DocumentHandler;
import com.lance.game.module.item.config.ItemConfig;
import org.bson.Document;
import org.springframework.stereotype.Repository;

/**
 * @author Lance
 */
@Repository
public class ItemManager implements IItemManager {

    @Override
    public ItemConfig getItemConfig(int id) {
        return MongoUtils.findOne("game", "item", "{id:" + id + "}", new DocumentHandler<ItemConfig>() {
            @Override
            public ItemConfig handle(Document doc) {
                ItemConfig itemConfig = new ItemConfig();
                itemConfig.setId(doc.getInteger("id"));
                itemConfig.setName(doc.getString("name"));
                itemConfig.setType(doc.getInteger("type"));
                return itemConfig;
            }
        });
    }
}
