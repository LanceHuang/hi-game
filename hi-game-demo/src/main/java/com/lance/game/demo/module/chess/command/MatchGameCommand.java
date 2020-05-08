package com.lance.game.demo.module.chess.command;

import com.lance.game.demo.GameContext;

/**
 * 请求匹配游戏
 *
 * @author Lance
 */
public class MatchGameCommand extends AbstractCommand {

    /** 账号 */
    private String account;

    public static MatchGameCommand valueOf(String account) {
        MatchGameCommand cmd = new MatchGameCommand();
        cmd.account = account;
        return cmd;
    }

    @Override
    public void action() {
        GameContext.getChessService().handleMatchCommand(this.account);
    }

}
