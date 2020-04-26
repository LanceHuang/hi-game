package com.lance.game.demo.log;

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

        DailyRollingDirectoryAppender appender = new DailyRollingDirectoryAppender();
        appender.setName(getAppenderName());
        appender.setThreshold(Level.INFO);
        appender.setBasePath("./module-logs/");
        appender.setDatePattern("yyyy-MM-dd");
        appender.setFile(getLoggerName() + ".log");
        appender.setLayout(patternLayout);
        appender.activateOptions(); // 使其生效

        Logger newLogger = LogManager.getLogger(getLoggerName());
        newLogger.setLevel(Level.INFO);
        newLogger.addAppender(appender);
        newLogger.setAdditivity(false); // 不会输出到父logger
        return newLogger;
    }

    private String getLoggerName() {
        return this.name().toLowerCase();
    }

    private String getAppenderName() {
        return getLoggerName() + "Appender";
    }

//    @Deprecated
//    private String getBasePath() {
//        String logDir = System.getProperty("logdir");
//        if (logDir == null) {
//            return "./module-logs/";
//        }
//        return logDir + "/module-logs/";
//    }
}
