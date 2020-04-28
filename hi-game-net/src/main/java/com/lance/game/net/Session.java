package com.lance.game.net;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 会话
 *
 * @author Lance
 */
public class Session {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    /** 会话id */
    private int    id;
    /** 会话ip */
    private String ip;
    /** 会话端口号 */
    private int    port;
    /** 创建时间 */
    private long   createTime;

    /** 会话数据 */
    private Map<String, Object> attributes = new HashMap<>();

    @Deprecated
    public static final String KEY_IDENTIFY = "IDENTIFY";

    // TODO Channel？
    public Session() {
        this.id = ID_GENERATOR.getAndIncrement();
        this.createTime = System.currentTimeMillis();
        // TODO ?
    }

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public void setAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    //============================= Getter/Setter ============================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", createTime=" + createTime +
                ", attributes=" + attributes +
                '}';
    }
}
