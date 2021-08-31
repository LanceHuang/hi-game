package com.lance.game.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 游戏上下文：游戏项目会在各种地方用到service,manager，甚至是实体。这些类并不希望直接依赖相应的对象，所以需要一个可集中管理的上下文
 *
 * @author Lance
 * @since 2021/8/31
 */
@Component
public class GameContext {

    private static GameContext instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static GameContext getInstance() {
        return instance;
    }

    //---------------------------------------------------------------------

    @Autowired
    private ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }

    @Autowired
    private GameService gameService;

    public static GameService getGameService() {
        return instance.gameService;
    }
}

