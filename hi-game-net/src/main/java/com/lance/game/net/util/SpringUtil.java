package com.lance.game.net.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * spring工具类，之前做游戏时都是用SpringContext直接存service引用，因为这样不需要再去查BeanFactory，性能高。
 * 而且游戏项目引用难有层级关系，有可能会在工具类中调用到其他service的功能，没办法初始化工具类，所以用SpringContext比较合适。
 * 但感觉暂时不需要这个类
 *
 * @author Lance
 * @since 2019/12/13 15:57
 */
@Deprecated
//@Component
public class SpringUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static SpringUtil instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static SpringUtil getInstance() {
        return instance;
    }

    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
