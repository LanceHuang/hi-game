package com.lance.net.controller;

import com.lance.game.module.player.model.Player;
import com.lance.net.Session;
import com.lance.net.annotation.GameController;
import com.lance.net.annotation.GameHandler;
import com.lance.net.protocol.TestRequest;
import com.lance.net.service.ITestService;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2019/10/23 22:07
 */
@GameController(module = 1)
public class TestController {

    @Resource
    private ITestService testService;

    @GameHandler(1000)
    public void test(Session session, TestRequest req) {
        Player player = session.getPlayer(); // todo
        if (player == null) {
            return;
        }

        testService.test(player, req.getMsg());
    }
}
