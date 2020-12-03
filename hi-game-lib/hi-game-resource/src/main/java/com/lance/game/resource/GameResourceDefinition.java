package com.lance.game.resource;

/**
 * 资源定义
 *
 * @author Lance
 * @since 2020/12/3
 */
public class GameResourceDefinition {

    /** 资源类 */
    private Class<?> clazz;

    /** 资源路径 */
    private String path;

    public GameResourceDefinition(Class<?> clazz, String path) {
        this.clazz = clazz;
        this.path = path;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
