package com.lance.game.net;

/**
 * 协议定义
 *
 * @author Lance
 */
public class ProtocolDefinition {

    private Class<?> clazz;



    private boolean init;

    public ProtocolDefinition(Class<?> clazz) {
        this.clazz = clazz;
    }

    // todo 缓存field？
    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
