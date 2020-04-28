package com.lance.game.net;

import java.util.HashMap;
import java.util.Map;

/**
 * 协议容器
 *
 * @author Lance
 */
public class ProtocolContainer {

    // TODO 用反射生成handler吧，HandlerMapping
    // TODO 若客户端发了错误协议，也要考虑一下怎么处理
    // TODO 懒加载？

    /** 注册的协议定义 */
    private Map<Integer, ProtocolHandler> handlers = new HashMap<>();

    /**
     * 注册协议处理器
     *
     * @param id      协议号
     * @param handler 处理器
     */
    public void registerHandler(int id, ProtocolHandler handler) {
        this.handlers.put(id, handler);
    }

    /**
     * 获取协议处理器
     *
     * @param id 协议号
     */
    public ProtocolHandler getHandler(int id) {
        return this.handlers.get(id);
    }

    public Map<Integer, ProtocolHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<Integer, ProtocolHandler> handlers) {
        this.handlers = handlers;
    }
}
