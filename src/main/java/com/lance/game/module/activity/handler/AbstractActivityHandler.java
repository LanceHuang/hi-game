package com.lance.game.module.activity.handler;

import javax.annotation.PostConstruct;

/**
 * @author Lance
 * @since 2019/9/5 21:02
 */
public abstract class AbstractActivityHandler implements IActivityHandler {

//    @PostConstruct

    @Override
    public void start() {
//        startInternal();
    }

    public abstract void startInternal();

    @Override
    public void stop() {
//        stopInternal();
    }

    public abstract void stopInternal();
}
