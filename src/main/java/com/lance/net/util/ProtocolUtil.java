package com.lance.net.util;

import com.lance.game.module.player.model.Player;

/**
 * 协议工具类
 *
 * @author Lance
 * @since 2019/10/24 10:42
 */
public class ProtocolUtil {

    private ProtocolUtil() {
    }

    public static void send(Player player, Object resp) {
        if (player == null) {
            return;
        }
        // todo
    }

    public static void send(String account, Object resp) {
        // todo
    }
}
