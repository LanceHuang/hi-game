package com.lance.game.resource.config;

import com.lance.game.resource.ResourceInjectProcessor;
import com.lance.game.resource.ResourceScanner;
import com.lance.game.resource.ResourceContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Lance
 * @since 2020/12/3
 */
@EnableConfigurationProperties(ResourceProperties.class)
public class ResourceAutoConfiguration {

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
