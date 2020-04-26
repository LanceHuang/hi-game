package com.lance.game.demo.util;

import com.lance.common.tool.util.JsonUtils;
import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.handler.ItemDocumentHandler;
import org.junit.Test;

import java.util.List;

public class MongoUtilsTest {

    private String databaseName   = "game";
    private String collectionName = "item";

    @Test
    public void insert() throws Exception {
        String[] data = {
                "{id: 1, name: \"石头\", type: 1}",
                "{id: 2, name: \"树叶\", type: 1}",
                "{id: 2001, name: \"小型生命药水\", type: 2, value: {HP: 100}}",
                "{id: 2002, name: \"中型生命药水\", type: 2, value: {HP: 500}}",
                "{id: 2003, name: \"大型生命药水\", type: 2, value: {HP: 1000}}",
                "{id: 2004, name: \"小型法力药水\", type: 2, value: {MP: 50}}",
                "{id: 3001, name: \"新手刀\", type: 3, pos:1, attribute: {ATK: 100}}",
                "{id: 3003, name: \"新手鞋\", type: 3, pos:2, attribute: {DEFENCE: 50, HP: 100}}"
        };

        for (String item : data) {
            MongoUtils.insert(databaseName, collectionName, JsonUtils.json2object(item, ItemConfig.class), new ItemDocumentHandler());
        }
    }

    @Test
    public void findOne() {
        String filter = "{id:2004}";

        ItemConfig itemConfig = MongoUtils.findOne(databaseName, collectionName, filter, new ItemDocumentHandler());
        System.out.println(itemConfig);
        System.out.println(itemConfig.getId());
        System.out.println(itemConfig.getName());
        System.out.println(itemConfig.getType());
    }

    @Test
    public void find() {
        List<ItemConfig> itemConfigs = MongoUtils.find(databaseName, collectionName, new ItemDocumentHandler());
        itemConfigs.forEach(System.out::println);
    }

    @Test
    public void deleteMany() {
        MongoUtils.deleteMany(databaseName, collectionName, "{type:2,name:'大型生命药水'}");
        System.out.println("=================================");
        find();
    }

    @Test
    public void findOneAndReplace() throws Exception {
        find();
        System.out.println("=================================");
        MongoUtils.findOneAndReplace(
                databaseName, collectionName, "{type:2}",
                JsonUtils.json2object("{id: 2001, name: \"小型生命药水\", type: 2, value: {HP: 100}}", ItemConfig.class),
                new ItemDocumentHandler()
        );
        System.out.println("=================================");
        find();
    }
}