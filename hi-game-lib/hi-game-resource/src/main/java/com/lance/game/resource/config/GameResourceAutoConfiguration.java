package com.lance.game.resource.config;

import com.lance.game.resource.GameResourceProcessor;
import com.lance.game.resource.GameResourceStorageManager;
import org.springframework.context.annotation.Bean;

/**
 * 资源配置项
 *
 * @author Lance
 * @since 2020/12/3
 */
public class GameResourceAutoConfiguration {

    @Bean
    public GameResourceStorageManager gameResourceStorageManager() {
        return new GameResourceStorageManager();
    }

    @Bean
    public GameResourceProcessor gameResourceProcessor() {
        GameResourceProcessor gameResourceProcessor = new GameResourceProcessor();
        gameResourceProcessor.setBasePackage("");
        gameResourceProcessor.setSuffix("xlsx");
        return gameResourceProcessor;
    }
}
