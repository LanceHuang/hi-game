package com.lance.game.resource.config;

import com.lance.game.resource.reader.ResourceReader;

/**
 * 资源配置
 *
 * @author Lance
 * @since 2020/12/22
 */
public class ResourceProperties {

    /** 资源类路径 */
    private String basePackage;

    /** 资源配置路径 */
    private String resourcePath;

    /** 资源解析类 */
    private Class<? extends ResourceReader> reader;

    /** 资源文件后缀 */
    private String suffix;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Class<? extends ResourceReader> getReader() {
        return reader;
    }

    public void setReader(Class<? extends ResourceReader> reader) {
        this.reader = reader;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
