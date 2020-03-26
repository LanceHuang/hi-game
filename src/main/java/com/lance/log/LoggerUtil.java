package com.lance.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志工具类
 *
 * @author Lance
 */
public final class LoggerUtil {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerUtil.class); // todo 调整一下日志层级，不然%l会输出LoggerUtil

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");

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
            Date now = new Date();
            logModule.getLogger().info("time:" + now.getTime() + ",date:" + sdf.format(now) + ",code:" + logCode.getCode() + "," + ft.getMessage());
        }
    }

}
