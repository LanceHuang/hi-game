package com.lance.game.net;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 游戏上下文
 *
 * @author Lance
 */
public class GameContext {

    private Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    public void putSession(String key, Session session) {
        this.sessionMap.put(key, session);
    }

    public Session getSession(String key) {
        return this.sessionMap.get(key);
    }

    //==========================

    public Map<String, Session> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Session> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
