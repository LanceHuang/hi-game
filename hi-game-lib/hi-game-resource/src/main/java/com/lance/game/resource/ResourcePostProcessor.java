package com.lance.game.resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * 资源处理
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ResourcePostProcessor implements ApplicationContextAware, InitializingBean, BeanFactoryPostProcessor {

    private ApplicationContext applicationContext;

    /** 资源包 */
    private String basePackage;

    /** 资源配置路径 */
    private String resourcePath;

    /** 资源文件后缀 */
    private String suffix;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(basePackage, "basePackage is empty.");
        Assert.hasLength(resourcePath, "resourcePath is empty.");
        Assert.hasLength(suffix, "suffix is empty.");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ResourceScanner resourceScanner = new ResourceScanner();
        resourceScanner.setApplicationContext(applicationContext);
        resourceScanner.setBasePackage(basePackage);
        resourceScanner.setResourcePath(resourcePath);
        resourceScanner.setSuffix(suffix);
        resourceScanner.scan();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
