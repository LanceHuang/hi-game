package com.lance.game.demo.module.chess.service;

import com.lance.game.demo.GameContext;
import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.module.chess.command.MatchGameCommand;
import com.lance.game.demo.module.chess.manager.IChessManager;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.util.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class ChessService implements IChessService {

    @Resource
    private IChessManager chessManager;

    @Override
    public void match(Player player) {
        Assert.notNull(player, I18nId.ERROR);

        GameContext.getCommandExecutor().submit(player.getAccount(), MatchGameCommand.valueOf(player.getAccount()));
    }

    @Override
    public void handleMatchCommand(String account) {
        // TODO: 2020/5/8
    }

    @Override
    public void buy(Player player, int id) {
        Assert.notNull(player, I18nId.ERROR);

    }

    @Override
    public void ready(Player player) {
        Assert.notNull(player, I18nId.ERROR);

    }
}
