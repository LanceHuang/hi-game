package com.lance.game.demo.module.player.dao;

import com.lance.game.demo.module.player.model.Player;
import com.lance.game.mongodb.annotation.FindOne;
import com.lance.game.mongodb.annotation.FindOneAndReplace;
import com.lance.game.mongodb.annotation.InsertOne;
import com.lance.game.mongodb.annotation.MongoDao;

/**
 * @author Lance
 */
@MongoDao(databaseName = "db_game", collectionName = "c_player", modelClass = Player.class)
public interface IPlayerDao {

    @InsertOne
    void addPlayer(Player player);

    @FindOne
    Player getPlayer(String filter);

    @FindOneAndReplace
    void replacePlayer(String filter, Player player);
}
