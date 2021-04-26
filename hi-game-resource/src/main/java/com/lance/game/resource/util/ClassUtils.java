package com.lance.game.resource.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 类处理工具
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ClassUtils extends org.springframework.util.ClassUtils {

    public static final String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
    public static final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
    public static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
    private static final MetadataReaderFactory METADATA_READER_FACTORY = new CachingMetadataReaderFactory();

    /**
     * 扫描符合条件的类
     *
     * @param basePackagePattern 扫描路径（com.lance.item;com.lance.horse）
     * @param classFilter        类过滤器
     */
    public static Collection<Class<?>> scan(String basePackagePattern, ClassFilter classFilter) {
        if (classFilter == null) {
            return null;
        }

        Set<Class<?>> result = new HashSet<>(); // 扫描结果
        // com/lance/item
        String[] basePackages = StringUtils.tokenizeToStringArray(basePackagePattern, CONFIG_LOCATION_DELIMITERS);
        for (String basePackage : basePackages) {
            try {
                // classpath*:com/lance/item/**/*.class
                String packageSearchPath = CLASSPATH_ALL_URL_PREFIX + convertClassNameToResourcePath(basePackage) + '/' + DEFAULT_RESOURCE_PATTERN;
                Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(packageSearchPath);
                for (Resource resource : resources) {
                    ClassMetadata classMetadata = METADATA_READER_FACTORY.getMetadataReader(resource).getClassMetadata();
                    Class<?> clazz = Class.forName(classMetadata.getClassName());
                    if (classFilter.accept(clazz)) {
                        result.add(clazz);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
