package com.lance.game.module.player.handler;

import com.lance.common.util.DocumentHandler;
import com.lance.game.module.player.model.Player;
import org.bson.Document;

/**
 * @author Lance
 */
public class PlayerDocumentHandler implements DocumentHandler<Player> {

    @Override
    public Document parse(Player data) {
        Document doc = new Document();
        doc.append("id", data.getId());
        doc.append("account", data.getAccount());
        doc.append("nickname", data.getNickname());
        doc.append("gender", data.getGender());
        doc.append("level", data.getLevel());
        doc.append("exp", data.getExp());
        return doc;
    }

    @Override
    public Player handle(Document doc) {
        Player player = new Player();
        player.setId(doc.getLong("id"));
        player.setAccount(doc.getString("account"));
        player.setNickname(doc.getString("nickname"));
        player.setGender(doc.getInteger("gender"));
        player.setLevel(doc.getInteger("level"));
        player.setExp(doc.getLong("exp"));
        return player;
    }
}
