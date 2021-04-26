package com.lance.game.mongodb.util;

import com.lance.game.mongodb.exception.ResolvePackageFailureException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lance
 */
public class ResourceUtils {

    private static final ClassFilter DEFAULT_CLASS_FILTER = new DefaultClassFilter();

    /**
     * 扫描包下所有类
     */
    public static List<Class<?>> resolvePackage(String basePackage) {
        return resolvePackage(basePackage, DEFAULT_CLASS_FILTER);
    }

    /**
     * 扫描包下的类
     */
    public static List<Class<?>> resolvePackage(String basePackage, ClassFilter filter) {
        if (basePackage == null || filter == null) {
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
                if (filter.accept(clazz)) {
                    candidates.add(clazz);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new ResolvePackageFailureException("扫描类失败：" + basePackage, e);
        }
        return candidates;
    }
}
