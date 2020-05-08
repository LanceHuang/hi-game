package com.lance.game.demo.module.chess.manager;

import com.lance.game.demo.module.chess.config.ChessConfig;
import com.lance.game.demo.module.chess.config.ChessRestraintConfig;

/**
 * @author Lance
 */
public interface IChessManager {

    /**
     * @param id 棋子id
     */
    ChessConfig getChessConfigById(int id);

    /**
     * @param type          类型
     * @param restraintType 克制类型
     */
    ChessRestraintConfig getChessRestraintConfigByType(int type, int restraintType);
}
