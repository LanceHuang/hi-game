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

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public void putAttribute(String name, String value) {
        this.attributes.put(name, value);
    }
}
