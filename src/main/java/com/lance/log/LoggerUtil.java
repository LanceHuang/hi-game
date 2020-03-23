package com.lance.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * 日志工具类
 *
 * @author Lance
 */
public final class LoggerUtil {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerUtil.class); // todo 调整一下日志层级，不然%l会输出LoggerUtil

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

    public static void log(LogModule logModule, LogCode logCode, String msg, Object... args) {
        if (logModule == null || logModule.getLogger() == null || logCode == null) {
            return;
        }

        if (logModule.getLogger().isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(msg, args);
            logModule.getLogger().info("time:" + System.currentTimeMillis() + ",code:" + logCode.getCode() + "," + ft.getMessage());
        }
    }

}
