package com.lance.game.resource.config;

import com.lance.game.resource.ResourceInjectProcessor;
import com.lance.game.resource.ResourceScanner;
import com.lance.game.resource.ResourceContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceAutoConfiguration {

    @Bean
    public ResourceProperties resourceProperties() {
        ResourceProperties properties = new ResourceProperties();
        properties.setBasePackage("com.lance.game.resource"); // TODO 可以配成xx.propertis；如果用springboot，可以配成ConfigurationProperties
        properties.setResourcePath("/resource/");
        properties.setSuffix(".xlsx");
        return properties;
    }

    @Bean
    public ResourceContext resourceScanner(ResourceProperties properties) {
        ResourceContext resourceContext = new ResourceContext();
        ResourceScanner resourceScanner = new ResourceScanner();
        resourceScanner.setContext(resourceContext);
        resourceScanner.setProperties(properties);
        resourceScanner.scan();
        return resourceContext;
    }

    @Bean
    public ResourceInjectProcessor resourceInjectProcessor() {
        return new ResourceInjectProcessor();
    }
}
