package com.lance.game.net;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 会话
 *
 * @author Lance
 */
@Getter
@Setter
public class Session {

    /** 会话id */
    private int id;

    /** ip */
    private String ip;

    /** 端口号 */
    private int port;

    /** 创建时间 */
    private long createTime;

    /** 会话数据 */
    private Map<String, Object> attributes = new HashMap<>();

    // TODO Channel？

    /**
     * 获取属性
     *
     * @param key 键
     * @return 值
     */
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    /**
     * 设置属性
     *
     * @param key   键
     * @param value 值
     */
    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    /**
     * 发送消息
     */
    public void send(Object msg) {
        // todo
    }
}
