package com.lance.game.net;

import com.lance.game.net.annotation.Protocol;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProtocolContainerTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 扫描目录下的所有类
        String basePackage = "com.lance.game.net";
        String searchPath = "classpath*:" + basePackage.replace('.', '/') + "/*.class";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory factory = new CachingMetadataReaderFactory(resolver);
        for (Resource resource : resolver.getResources(searchPath)) {
            MetadataReader reader = factory.getMetadataReader(resource);
            String className = reader.getClassMetadata().getClassName();

            // 判断是否标注了Protocol
            Class<?> clazz = Class.forName(className);
            if (!clazz.isAnnotationPresent(Protocol.class)) {
                continue;
            }

            // 注册到容器
            Protocol protocol = clazz.getDeclaredAnnotation(Protocol.class);
            System.out.println(protocol.value() + " " + clazz);
        }
    }
}