package com.lance.game.resource.config;

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

    /** 资源解析类型 */
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
