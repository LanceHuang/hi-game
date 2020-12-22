package com.lance.game.resource;

/**
 * 资源定义
 *
 * @author Lance
 * @since 2020/12/3
 */
public class ResourceDefinition {

    /** 资源类 */
    private Class<?> clazz;

    /** 资源解析类型 */
    private String type;

    /** 资源路径 */
    private String path;

    public ResourceDefinition(Class<?> clazz, String type, String path) {
        this.clazz = clazz;
        this.type = type;
        this.path = path;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
