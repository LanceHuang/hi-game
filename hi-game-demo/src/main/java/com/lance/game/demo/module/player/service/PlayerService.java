package com.lance.game.demo.module.player.service;

import com.lance.game.demo.module.player.manager.IPlayerManager;
import com.lance.game.demo.module.player.model.Player;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class PlayerService implements IPlayerService {

    @Resource
    private IPlayerManager playerManager;

    @Override
    public void savePlayer(Player player) {
        playerManager.savePlayer(player);
    }

    @Override
    public Player getPlayer(long id) {
        return playerManager.getPlayer(id);
    }
}
