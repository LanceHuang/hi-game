package com.lance.game.demo.module.chess.manager;

import com.lance.game.demo.module.chess.config.ChessConfig;

/**
 * @author Lance
 */
public interface IChessManager {

    ChessConfig getChessConfigById(int id);

    ChessConfig getChessRestraintConfigByType(int aType, int bType);
}
