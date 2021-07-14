package com.lance.game.lab.event.exception;

import com.lance.game.lab.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class DefaultExceptionHandler implements ExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleError(Event event, Throwable t) {
        logger.error("Event error, source:[{}], thread:[{}]", event.logString(), Thread.currentThread().getName(), t);
    }
}
