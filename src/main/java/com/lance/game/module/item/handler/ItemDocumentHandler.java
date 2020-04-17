package com.lance.game.module.item.handler;

import com.lance.common.util.DocumentHandler;
import com.lance.game.module.item.config.ItemConfig;
import org.bson.Document;

/**
 * @author Lance
 */
public class ItemDocumentHandler implements DocumentHandler<ItemConfig> {

    @Override
    public Document parse(ItemConfig data) {
        Document doc = new Document();
        doc.append("id", data.getId());
        doc.append("name", data.getName());
        doc.append("type", data.getType());
        return doc;
    }

    @Override
    public ItemConfig handle(Document doc) {
        ItemConfig itemConfig = new ItemConfig();
        itemConfig.setId(doc.get("id", 0));
        itemConfig.setName(doc.getString("name"));
        itemConfig.setType(doc.get("type", 0));
        return itemConfig;
    }
}
