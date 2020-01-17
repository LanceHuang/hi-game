//package com.lance.net.service;
//
//import com.lance.game.module.player.model.Player;
//import com.lance.common.log.task.LoggerUtil;
//import com.lance.net.protocol.TestResponse;
//import com.lance.net.task.ProtocolUtil;
//import org.springframework.stereotype.Service;
//
///**
// * @author Lance
// * @since 2019/10/23 22:18
// */
//@Service
//public class TestService implements ITestService {
//
//    @Override
//    public void test(Player player, String msg) {
//        LoggerUtil.info("{} 发送了消息 {}", player.getNickname(), msg);
//
//        ProtocolUtil.send(player, TestResponse.create(true));
//    }
//}
