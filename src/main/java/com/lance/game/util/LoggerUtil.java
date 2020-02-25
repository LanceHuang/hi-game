package com.lance.game.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author Lance
 * @since 2019/10/24 10:32
 */
public class LoggerUtil {

    // TODO: 2020/2/24 调整一下输出格式
    private static final Logger LOG = LoggerFactory.getLogger(LoggerUtil.class);

    private LoggerUtil() {
    }

    public static void debug(String msg, Object... args) {
        LOG.debug(msg, args);
    }

    public static void info(String msg, Object... args) {
        LOG.info(msg, args);
    }

    public static void error(String msg, Object... args) {
        LOG.error(msg, args);
    }
}