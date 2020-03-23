package com.lance.log;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * 日志模块
 *
 * @author Lance
 */
public enum LogModule {

    TEST,
    BUFF,
    ;

    private Logger logger;

    public Logger getLogger() {
        if (this.logger != null) {
            return this.logger;
        }

        this.logger = createLogger();
        return this.logger;
    }

    private Logger createLogger() {
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%m%n");

        FileAppender appender = new FileAppender();
        appender.setName(getAppenderName());
        appender.setThreshold(Level.INFO);
        appender.setFile(getFileName());
        appender.setLayout(patternLayout);
        appender.activateOptions(); // 使其生效

        Logger newLogger = LogManager.getLogger(getLoggerName());
        newLogger.setLevel(Level.INFO);
        newLogger.addAppender(appender);
        newLogger.setAdditivity(false);
        return newLogger;
    }

    private String getLoggerName() {
        return this.name().toLowerCase();
    }

    private String getAppenderName() {
        return getLoggerName() + "Appender";
    }

    private String getFileName() {
        String logDir = System.getProperty("logdir");
        if (logDir == null) {
            logDir = ".";
        }
        return logDir + "/module-logs/" + getLoggerName() + ".log";
    }
}
