package com.lance.game.lab.event.executor;

import com.lance.game.lab.event.Event;
import com.lance.game.lab.event.invoker.EventListenerInvoker;

/**
 * 同步事件执行器
 *
 * @author Lance
 * @since 2021/7/14
 */
public class SyncEventListenerExecutor extends AbstractEventListenerExecutor {

    @Override
    public void invokeListener(EventListenerInvoker invoker, Event event) {
        try {
            invoker.invokeListener(event);
        } catch (Exception e) {
            getExceptionHandler().handleError(event, e);
        }
    }
}
