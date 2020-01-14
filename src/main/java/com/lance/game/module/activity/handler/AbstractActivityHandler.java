package com.lance.game.module.activity.handler;

import com.lance.game.module.activity.constant.ActivityType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2019/9/5 21:02
 */
public abstract class AbstractActivityHandler implements IActivityHandler {

    private static final Map<ActivityType, AbstractActivityHandler> HANDLERS = new HashMap<>();

    @PostConstruct
    public void init() {
        HANDLERS.put(getActivityType(), this);
    }

    public static AbstractActivityHandler getActivityHandler(ActivityType type) {
        return HANDLERS.get(type);
    }

//    @Override
//    public void start() {
////        startInternal();
//    }
//
//    public abstract void startInternal();

//    @Override
//    public void stop() {
////        stopInternal();
//    }

//    public abstract void stopInternal();

}
