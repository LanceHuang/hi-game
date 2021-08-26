package com.lance.game.event.exception;

import com.lance.game.event.Event;

/**
 * 异常处理器
 *
 * @author Lance
 * @since 2021/7/14
 */
public interface ExceptionHandler {

    /**
     * 处理事件异常
     *
     * @param event 事件
     * @param t     异常
     */
    void handleError(Event event, Throwable t);
}
