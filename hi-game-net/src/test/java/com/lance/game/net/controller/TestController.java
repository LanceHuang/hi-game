package com.lance.game.net.controller;

import com.lance.game.net.Session;
import com.lance.game.net.annotation.MessageListener;
import com.lance.game.net.message.ReqTestMessage;
import com.lance.game.net.message.RespTestMessage;
import org.springframework.stereotype.Component;

/**
 * @author Lance
 * @since 2021/4/25
 */
@Component
public class TestController {

    @MessageListener
    public void test(Session session, ReqTestMessage req) {
        // todo
        session.send(new RespTestMessage());
    }
}
