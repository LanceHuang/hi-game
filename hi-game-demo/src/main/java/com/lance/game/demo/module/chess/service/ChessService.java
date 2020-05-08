package com.lance.game.demo.module.chess.service;

import com.lance.game.demo.GameContext;
import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.chess.command.MatchGameCommand;
import com.lance.game.demo.module.chess.manager.IChessManager;
import com.lance.game.demo.module.player.model.Player;
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
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }

        GameContext.getCommandExecutor().submit(player.getAccount(), MatchGameCommand.valueOf(player.getAccount()));
    }

    @Override
    public void handleMatchCommand(String account) {
        // TODO: 2020/5/8
    }

    @Override
    public void buy(Player player, int id) {
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }

    }

    @Override
    public void ready(Player player) {
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }

    }
}
