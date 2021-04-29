package com.lance.game.net.message;

import com.lance.game.common.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 消息扫描
 *
 * @author Lance
 * @since 2021/4/29
 */
public class MessageScanner {

    private final MessageManager messageManager;

    public MessageScanner(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    /**
     * 扫描消息类
     *
     * @param basePackages 扫描包
     * @param annotation   消息注解
     */
    public void scan(String[] basePackages, Class<? extends Annotation> annotation) {
        for (String basePackage : basePackages) {
            List<Class<?>> matchClasses = ClassUtils.resolvePackage(basePackage, clazz -> clazz.isAnnotationPresent(annotation));
            matchClasses.forEach(messageManager::registerMessage);
        }
    }
}
