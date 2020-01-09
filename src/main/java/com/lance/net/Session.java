package com.lance.net;

import com.lance.game.module.player.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * 会话
 *
 * @author Lance
 * @since 2019/10/25 11:24
 */
public class Session {

    private Map<String, Object> attributes = new HashMap<>();

    public static final String KEY_IDENTIFY = "IDENTIFY";

    public Player getPlayer() {
        return (Player) attributes.get(KEY_IDENTIFY);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttribute(String name, String value) {
        attributes.put(name, value);
    }

}
