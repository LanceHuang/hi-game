package com.lance.game.common.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Lance
 */
public class ClassUtils {

    /**
     * 扫描包下的所有类
     *
     * @param basePackage 扫描路径
     * @return 符合条件的类
     */
    public static List<Class<?>> resolvePackage(String basePackage, Predicate<Class<?>> predicate) {
        if (basePackage == null || basePackage.isEmpty() || predicate == null) {
            return Collections.emptyList();
        }

        // 将包名转换成路径
        String path = "classpath*:" + basePackage.replace('.', '/') + "/**/*.class";

        // 筛选出路径下的所有类
        List<Class<?>> candidates = new LinkedList<>();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        try {
            for (Resource resource : resolver.getResources(path)) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                ClassMetadata classMetadata = metadataReader.getClassMetadata();

                // 判断是否满足条件
                Class<?> clazz = Class.forName(classMetadata.getClassName());
                if (predicate.test(clazz)) {
                    candidates.add(clazz);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to resolve package: " + basePackage, e);
        }
        return candidates;
    }
}
