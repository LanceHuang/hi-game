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

    /** 活动处理器，运行时只读，线程安全 */
    private static final Map<Integer, AbstractActivityHandler> HANDLERS = new HashMap<>();

    /**
     * 初始化，注册活动处理器
     */
    @PostConstruct
    protected void init() {
        HANDLERS.put(getActivityType().getId(), this);
    }

    /**
     * 活动类型
     */
    public abstract ActivityType getActivityType();

    /**
     * 获得活动处理器
     *
     * @param type 活动类型
     */
    public static AbstractActivityHandler getHandler(int type) {
        return HANDLERS.get(type);
    }

    // todo 判断活动状态

    // todo 玩家能否参加活动
    // todo 玩家领取奖励？


}
