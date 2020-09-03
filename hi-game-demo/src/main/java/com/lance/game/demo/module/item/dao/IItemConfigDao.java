package com.lance.game.demo.module.item.dao;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.mongodb.annotation.DeleteMany;
import com.lance.game.mongodb.annotation.DeleteOne;
import com.lance.game.mongodb.annotation.FindMany;
import com.lance.game.mongodb.annotation.FindOne;
import com.lance.game.mongodb.annotation.FindOneAndReplace;
import com.lance.game.mongodb.annotation.InsertOne;
import com.lance.game.mongodb.annotation.MongoDao;

import java.util.List;

/**
 * 道具配置
 *
 * @author Lance
 */
@MongoDao(databaseName = "db_game", collectionName = "c_item", modelClass = ItemConfig.class)
public interface IItemConfigDao {

//  todo  推荐提供一个公共的接口，里面提供所有的常见的方法，类似于MyBatis plus的BaseMapper

    @InsertOne
    void addItemConfig(ItemConfig config);

    @FindOne
    ItemConfig getItemConfig(String filter);

    @FindMany
    List<ItemConfig> getItemConfigs(String filter);

    @FindOneAndReplace
    void replaceItemConfig(String filter, ItemConfig itemConfig);

    @DeleteOne
    void deleteItemConfig(String filter);

    @DeleteMany
    void deleteItemConfigs(String filter);
}
