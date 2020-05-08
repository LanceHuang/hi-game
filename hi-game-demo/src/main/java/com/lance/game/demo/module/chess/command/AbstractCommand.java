package com.lance.game.demo.module.chess.command;

/**
 * 操作命令
 *
 * @author Lance
 */
public abstract class AbstractCommand implements Runnable {

    @Override
    public void run() {
        action();
    }

    public abstract void action();

}
