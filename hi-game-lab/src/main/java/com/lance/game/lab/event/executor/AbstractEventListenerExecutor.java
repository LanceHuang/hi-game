package com.lance.game.lab.event.executor;

import com.lance.game.lab.event.exception.DefaultExceptionHandler;
import com.lance.game.lab.event.exception.ExceptionHandler;

/**
 * @author Lance
 * @since 2021/7/14
 */
public abstract class AbstractEventListenerExecutor implements EventListenerExecutor {

    private ExceptionHandler exceptionHandler;

    public AbstractEventListenerExecutor() {
        this.exceptionHandler = new DefaultExceptionHandler();
    }

    public AbstractEventListenerExecutor(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
}
