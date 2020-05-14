package com.lance.game.demo.module.item.dao;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.orm.annotation.DeleteMany;
import com.lance.game.orm.annotation.DeleteOne;
import com.lance.game.orm.annotation.FindMany;
import com.lance.game.orm.annotation.FindOne;
import com.lance.game.orm.annotation.FindOneAndReplace;
import com.lance.game.orm.annotation.InsertOne;
import com.lance.game.orm.annotation.MongoDao;

import java.util.List;

/**
 * 道具配置
 *
 * @author Lance
 */
@MongoDao(databaseName = "db_game", collectionName = "c_item", modelClass = ItemConfig.class)
public interface IItemConfigDao {

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
