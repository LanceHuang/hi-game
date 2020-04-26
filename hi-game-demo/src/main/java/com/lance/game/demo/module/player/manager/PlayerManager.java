package com.lance.game.demo.module.player.manager;

import com.lance.game.demo.module.player.handler.PlayerDocumentHandler;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.util.MongoUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Lance
 */
@Repository
public class PlayerManager implements IPlayerManager {

    private static final String DATABASE_NAME   = "game";
    private static final String COLLECTION_NAME = "player";

    @Override
    public void savePlayer(Player player) {
        Player existsPlayer = MongoUtils.findOne(DATABASE_NAME, COLLECTION_NAME, "{id:" + player.getId() + "}", new PlayerDocumentHandler());

        try {
            if (existsPlayer != null) {
                MongoUtils.findOneAndReplace(DATABASE_NAME, COLLECTION_NAME, "{id:" + player.getId() + "}", player, new PlayerDocumentHandler());
            } else {
                MongoUtils.insert(DATABASE_NAME, COLLECTION_NAME, player, new PlayerDocumentHandler());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player getPlayer(long id) {
        return MongoUtils.findOne(DATABASE_NAME, COLLECTION_NAME, "{id:" + id + "}", new PlayerDocumentHandler());
    }
}
