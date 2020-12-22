package com.lance.game.resource.config;

import com.lance.game.resource.ResourcePostProcessor;
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
    public ResourceProperties resourceProperties() {
        ResourceProperties properties = new ResourceProperties();
        properties.setBasePackage(""); // TODO 可以配成xx.propertis；如果用springboot，可以配成ConfigurationProperties
        properties.setResourcePath("/resource/");
        properties.setSuffix("xlsx");
        return properties;
    }

    @Bean
    public ResourcePostProcessor resourceProcessor(ResourceProperties properties) {
        ResourcePostProcessor processor = new ResourcePostProcessor();
        processor.setBasePackage(properties.getBasePackage());
        processor.setResourcePath(properties.getResourcePath());
        processor.setSuffix(properties.getSuffix());
        return processor;
    }
}
