package com.lance.config.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 配置注入处理器
 *
 * @author Lance
 */
@Component
public class ConfigInjectProcessor extends InstantiationAwareBeanPostProcessorAdapter implements ApplicationContextAware, Ordered {

    /** 配置路径 */
    private String location;
    /** 解析器类型 */
    private String type;
    /** 类扫描路径 */
    private String scanPackage;

    private ApplicationContext ac;

    // todo 若其他bean先初始化，怎么办？

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // todo
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
