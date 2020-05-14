package com.lance.game.demo.module.player.manager;

import com.lance.game.demo.module.player.dao.IPlayerDao;
import com.lance.game.demo.module.player.model.Player;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Repository
public class PlayerManager implements IPlayerManager {

    @Resource
    private IPlayerDao playerDao;

    @Override
    public void savePlayer(Player player) {
        Player existsPlayer = playerDao.getPlayer("{id:" + player.getId() + "}");

        try {
            if (existsPlayer != null) {
                playerDao.replacePlayer("{id:" + player.getId() + "}", player);
            } else {
                playerDao.addPlayer(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player getPlayer(long id) {
        return playerDao.getPlayer("{id:" + id + "}");
    }
}
