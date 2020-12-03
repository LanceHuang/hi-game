package com.lance.game.resource;

import com.lance.game.resource.util.ClassUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 资源处理器
 *
 * @author Lance
 * @since 2020/12/3
 */
public class GameResourceProcessor implements ApplicationContextAware, InitializingBean, BeanFactoryPostProcessor {

    private ApplicationContext applicationContext;

    /** 扫描路径 */
    private String basePackage;

    /** 资源文件后缀 */
    private String suffix;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (basePackage == null || basePackage.isEmpty()) {
            basePackage = ClassUtils.getPackageName(this.getClass().getName());
        }
        if (suffix == null || suffix.isEmpty()) {
            throw new IllegalArgumentException("suffix is empty.");
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        GameResourceScanner gameResourceScanner = new GameResourceScanner();
        gameResourceScanner.setApplicationContext(applicationContext);
        gameResourceScanner.setBasePackage(basePackage);
        gameResourceScanner.setSuffix(suffix);
        gameResourceScanner.scan();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
