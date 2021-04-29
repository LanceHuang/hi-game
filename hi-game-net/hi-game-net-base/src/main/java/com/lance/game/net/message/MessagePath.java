package com.lance.game.net.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * 消息扫描包
 *
 * @author Lance
 * @since 2021/4/29
 */
@Getter
@Setter
@AllArgsConstructor
public class MessagePath {

    /** 扫描包 */
    private String[] basePackages;

    public static class Registrar implements ImportBeanDefinitionRegistrar {

        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableMessage.class.getName()));
            if (attributes == null) {
                return;
            }

            // 若未配置basePackages，默认使用启动类的包名
            String[] basePackages = attributes.getStringArray("basePackages");
            if (basePackages.length == 0) {
                basePackages = new String[1];
                basePackages[0] = ClassUtils.getPackageName(metadata.getClassName());
            }

            // 注册bean
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MessagePath.class);
            beanDefinitionBuilder.addConstructorArgValue(basePackages);
            registry.registerBeanDefinition(MessagePath.class.getName(), beanDefinitionBuilder.getBeanDefinition());
        }
    }
}
