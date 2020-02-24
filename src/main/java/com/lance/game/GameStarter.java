package com.lance.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// TODO: 2020/2/24 可以换成SpringBoot方式

/**
 * 启动器
 *
 * @author Lance
 */
public class GameStarter {

    private static final String DEFAULT_CONFIG_LOCATION = "application-context.xml";

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext(DEFAULT_CONFIG_LOCATION);
        IGameService gameService = GameContext.getBean(IGameService.class);
        gameService.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                gameService.stop();
            }
        });
    }

}
