package com.lance.game.net.session;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author Lance
 * @since 2021/5/7
 */
public class SessionUtils {

    private static final AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("SESSION_KEY");

    public static Session getSession(Channel channel) {
        Session session = channel.attr(SESSION_KEY).get();
        if (session == null) {
            channel.attr(SESSION_KEY).compareAndSet(null, new Session(channel));
            return channel.attr(SESSION_KEY).get();
        }
        return session;
    }
}
