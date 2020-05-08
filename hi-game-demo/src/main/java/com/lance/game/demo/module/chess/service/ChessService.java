package com.lance.game.demo.module.chess.service;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.module.player.model.Player;
import org.springframework.stereotype.Service;

/**
 * @author Lance
 */
@Service
public class ChessService implements IChessService {

    @Override
    public void match(Player player) {
        if (player == null) {
            throw new GameException(I18nId.ERROR);
        }

        // todo 并发问题

        // todo 同步的话，这里应该要上锁；任务的话，抛个任务到队列
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
