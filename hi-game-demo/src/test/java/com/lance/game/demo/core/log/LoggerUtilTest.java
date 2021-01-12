package com.lance.game.demo.core.log;

import org.junit.Test;

public class LoggerUtilTest {
    @Test
    public void test() {
        LoggerUtil.debug(null);
        LoggerUtil.debug(null, null);
        LoggerUtil.debug("测试debug");
        LoggerUtil.info("测试info");
        LoggerUtil.error("测试error");
        LoggerUtil.error("Helalsdfasd", new IllegalArgumentException("报错啦"));
    }

    @Test
    public void testLog() {
        LoggerUtil.log(LogModule.BUFF, LogCode.BUFF_ADD, "创建buff【{}】", 5642);
        LoggerUtil.log(LogModule.BUFF, LogCode.BUFF_REMOVE, "移除buff【{}】", 5642);
        LoggerUtil.log(LogModule.TEST, LogCode.TEST, "测试数据1");
        LoggerUtil.log(LogModule.TEST, LogCode.TEST, "测试数据2");
    }
}