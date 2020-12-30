package com.lance.game.demo;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 游戏服
 *
 * @author Lance
 * @since 2020/12/30
 */
@Component
public class GameServer implements SmartLifecycle {

    private boolean running;

    @Override
    public void start() {
        if (this.running) {
            return;
        }

        // todo

        this.running = true;
    }

    @Override
    public void stop() {
        if (!this.running) {
            return;
        }

        // todo

        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }
}
