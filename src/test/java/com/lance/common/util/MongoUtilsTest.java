package com.lance.common.util;

import com.lance.game.module.item.config.ItemConfig;
import org.bson.Document;
import org.junit.Test;

public class MongoUtilsTest {

    @Test
    public void insert() {
        String databaseName = "game";
        String collectionName = "item";
        String[] docs = {
                "{id: 1, name: \"石头\", type: 1}",
                "{id: 2, name: \"树叶\", type: 1}",
                "{id: 2001, name: \"小型生命药水\", type: 2, value: {HP: 100}}",
                "{id: 2002, name: \"中型生命药水\", type: 2, value: {HP: 500}}",
                "{id: 2003, name: \"大型生命药水\", type: 2, value: {HP: 1000}}",
                "{id: 2004, name: \"小型法力药水\", type: 2, value: {MP: 50}}",
                "{id: 3001, name: \"新手刀\", type: 3, pos:1, attribute: {ATK: 100}}",
                "{id: 3003, name: \"新手鞋\", type: 3, pos:2, attribute: {DEFENCE: 50, HP: 100}}"
        };

        for (String doc : docs) {
            MongoUtils.insert(databaseName, collectionName, doc);
        }
    }

    @Test
    public void findOne() {
        String databaseName = "game";
        String collectionName = "item";
        String query = "{id:2004}";

        ItemConfig itemConfig = MongoUtils.findOne(databaseName, collectionName, query, new ResultHandler<ItemConfig>() {
            @Override
            public ItemConfig handle(Document doc) {
                ItemConfig itemConfig = new ItemConfig();
                itemConfig.setId(doc.getInteger("id"));
                itemConfig.setName(doc.getString("name"));
                itemConfig.setType(doc.getInteger("type"));
                return itemConfig;
            }
        });

        System.out.println(itemConfig);
        System.out.println(itemConfig.getId());
        System.out.println(itemConfig.getName());
        System.out.println(itemConfig.getType());
    }
}