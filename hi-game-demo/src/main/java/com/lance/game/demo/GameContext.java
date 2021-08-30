package com.lance.game.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 游戏上下文：游戏项目会在各种地方用到service,manager，甚至是实体。这些类并不希望直接依赖相应的对象，所以需要一个可集中管理的上下文
 *
 * @author Lance
 */
@Component
public class GameContext {

    @Resource
    private ApplicationContext applicationContext;

    private static GameContext instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }
}
