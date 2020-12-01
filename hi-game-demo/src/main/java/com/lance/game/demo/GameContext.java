package com.lance.game.demo;

import com.lance.game.demo.executor.ICommandExecutor;
import com.lance.game.demo.module.chess.service.IChessService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 游戏上下文：游戏项目会在各种地方用到service,manager，甚至是实体。这些类并不希望直接依赖相应的对象，所以需要一个可集中管理的上下文
 *
 * @author Lance
 */
@Component
public class GameContext implements ApplicationContextAware {

    private static GameContext instance;

    private ApplicationContext applicationContext;

    @Resource
    private ICommandExecutor commandExecutor;

    @Resource
    private IChessService chessService;

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }

    public static ICommandExecutor getCommandExecutor() {
        return instance.commandExecutor;
    }

    public static IChessService getChessService() {
        return instance.chessService;
    }
}
