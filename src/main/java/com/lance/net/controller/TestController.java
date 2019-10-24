package com.lance.net.controller;

import com.lance.game.module.player.model.Player;
import com.lance.net.GameContext;
import com.lance.net.annotation.GameController;
import com.lance.net.protocol.TestRequest;
import com.lance.net.service.ITestService;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2019/10/23 22:07
 */
@GameController
public class TestController {

    @Resource
    private ITestService testService;

    // todo 外面再裹一层 try-catch

    public void test(GameContext ctx, TestRequest req) {
        Player player = ctx.getPlayer(); // todo
        if (player == null) {
            return;
        }

        testService.test(player, req.getMsg());
    }
}
