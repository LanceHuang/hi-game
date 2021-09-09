package com.lance.game.mud.cmd;

/**
 * 游戏指令类型
 *
 * @author Lance
 * @since 2021/9/9
 */
public enum GameCommandType {

    /** 生产 */
    MAKE,
    /** 建造 */
    BUILD,
    /** 移动 */
    MOVE,
    /** 站立不动 */
    STAND_BY,
    /** 攻击 */
    ATTCK,
    ;

    private final GameCommand gameCommand;

    GameCommandType(GameCommand gameCommand) {
        this.gameCommand = gameCommand;
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }
}
