package com.lance.game.orm;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lance
 */
public class MongoDaoScanner implements ApplicationContextAware, InitializingBean, BeanFactoryPostProcessor {

    private ApplicationContext applicationContext;

    private String basePackage;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //todo 参数校验
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // 1. 扫描符合条件的类
        List<Class<?>> candidates = scan();

        // 2. 生成代理类


        // 3. 注册到容器
//        configurableListableBeanFactory.registerSingleton();
    }

    /**
     * 扫描路径下所有符合条件的类
     */
    private List<Class<?>> scan() {
        List<Class<?>> candidates = new LinkedList<>();

        String path = "classpath*:" + this.basePackage;
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            for (Resource resource : resolver.getResources(path)) {

            }
        } catch (IOException e) {
            e.printStackTrace();
            // todo
        }
        return candidates;
    }


    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}
