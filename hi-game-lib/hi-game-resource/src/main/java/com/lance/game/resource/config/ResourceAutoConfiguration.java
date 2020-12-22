package com.lance.game.resource.config;

import com.lance.game.resource.ResourceProcessor;
import com.lance.game.resource.ResourceStorageManager;
import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceAutoConfiguration {

    @Bean
    public ResourceStorageManager resourceStorageManager() {
        return new ResourceStorageManager();
    }

    @Bean
    public ResourceProcessor resourceProcessor() {
        ResourceProcessor resourceProcessor = new ResourceProcessor();
        resourceProcessor.setBasePackage("");
        resourceProcessor.setSuffix("xlsx");
        return resourceProcessor;
    }
}
