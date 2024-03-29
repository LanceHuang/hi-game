package com.lance.game.mongodb;

import com.lance.game.common.util.ClassUtils;
import com.lance.game.mongodb.annotation.MongoDao;
import com.lance.game.mongodb.exception.GenerateProxyFailureException;
import com.lance.game.mongodb.generator.MongoDaoProxyGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.List;

/**
 * 扫描路径下标注了{@code com.lance.game.orm.annotation.MongoDao}的接口，生成代理类，并注册到Spring容器
 *
 * @author Lance
 */
public class MongoDaoScanner implements InitializingBean, BeanDefinitionRegistryPostProcessor {

    private String basePackage;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 参数校验
        if (this.basePackage == null) {
            throw new IllegalArgumentException("basePackage cannot be null");
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // Do nothing
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 1. 扫描符合条件的类
        List<Class<?>> candidates = ClassUtils.resolvePackage(this.basePackage, (clazz) -> clazz.isAnnotationPresent(MongoDao.class));

        // 2. 生成代理类
        for (Class<?> candidate : candidates) {
            Class<?> enhanceClass;
            try {
                enhanceClass = MongoDaoProxyGenerator.generateProxyClass(candidate);
            } catch (Exception e) {
                throw new GenerateProxyFailureException("无法生成代理类：" + candidate, e);
            }
            if (enhanceClass == null) {
                throw new GenerateProxyFailureException("生成代理类失败：" + candidate);
            }

            // 3. 注册到容器
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(enhanceClass);
            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            registry.registerBeanDefinition(enhanceClass.getSimpleName(), beanDefinition);
        }
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}
