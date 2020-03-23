package com.lance.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtilTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() {
        LoggerUtil.debug("测试debug");
        LoggerUtil.info("测试info");
        LoggerUtil.error("测试error");
        LoggerUtil.error("Helalsdfasd", new IllegalArgumentException("报错啦"));
    }

    @Test
    public void testLog() {
        LoggerUtil.log(LogModule.BUFF, "创建buff【{}】", 5642);
        LoggerUtil.log(LogModule.BUFF, "移除buff【{}】", 5642);
        LoggerUtil.log(LogModule.TEST, "测试数据1");
        LoggerUtil.log(LogModule.TEST, "测试数据2");
    }
}