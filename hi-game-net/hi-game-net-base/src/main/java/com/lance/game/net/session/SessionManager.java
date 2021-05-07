package com.lance.game.net.session;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/5/7
 */
public class SessionManager {

    private final Map<String, Session> sessionMap = new HashMap<>();

    private static SessionManager instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static SessionManager getInstance() {
        return instance;
    }

}
