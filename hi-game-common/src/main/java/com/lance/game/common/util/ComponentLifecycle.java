package com.lance.game.common.util;

import org.springframework.context.SmartLifecycle;

/**
 * 组件生命周期
 *
 * @author Lance
 * @since 2021/4/12
 */
public abstract class ComponentLifecycle implements SmartLifecycle {

    private volatile boolean running;

    @Override
    public void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        doStart();
    }

    public abstract void doStart();

    @Override
    public void stop() {
        if (!this.running) {
            return;
        }
        this.running = false;
        doStop();
    }

    public abstract void doStop();

    @Override
    public boolean isRunning() {
        return this.running;
    }
}
