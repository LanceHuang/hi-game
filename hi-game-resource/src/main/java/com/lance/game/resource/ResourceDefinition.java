package com.lance.game.resource;

import com.lance.game.resource.reader.ResourceReader;

/**
 * 资源定义
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceDefinition {

    /** 资源类 */
    private Class<?> clazz;

    /** 资源解析类 */
    private ResourceReader resourceReader;

    /** 资源路径 */
    private String path;

    public ResourceDefinition(Class<?> clazz, ResourceReader resourceReader, String path) {
        this.clazz = clazz;
        this.resourceReader = resourceReader;
        this.path = path;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ResourceReader getResourceReader() {
        return resourceReader;
    }

    public void setResourceReader(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
