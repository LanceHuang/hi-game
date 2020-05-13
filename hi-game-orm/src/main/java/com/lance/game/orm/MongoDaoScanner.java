package com.lance.game.orm;

import com.lance.game.orm.annotation.MongoDao;
import com.lance.game.orm.util.ResourceUtils;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.List;

/**
 * 扫描路径下标注了{@code com.lance.game.orm.annotation.MongoDao}的接口，生成代理类，并注册到Spring容器
 *
 * @author Lance
 */
public class MongoDaoScanner implements InitializingBean, BeanFactoryPostProcessor {

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
        // 1. 扫描符合条件的类
        List<Class<?>> candidates = ResourceUtils.resolvePackage(this.basePackage, (clazz) -> clazz.isAnnotationPresent(MongoDao.class));

        // 2. 生成代理类
        for (Class<?> candidate : candidates) {
            Class<?> enhanceClass = null;
            try {
                enhanceClass = MongoDaoProxyGenerator.generateProxyClass(candidate);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }

//            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(enhanceClass);
//            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
//            configurableListableBeanFactory.regist(enhanceClass.getName(), beanDefinition);

            System.out.println(enhanceClass);
            // 3. 注册到容器
        }
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}
