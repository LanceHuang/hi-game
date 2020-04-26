package com.lance.game.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 游戏上下文：游戏项目会在各种地方用到service,manager，甚至是实体。这些类并不希望直接依赖相应的对象，所以需要一个可集中管理的上下文
 *
 * @author Lance
 */
@Component
public class GameContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
