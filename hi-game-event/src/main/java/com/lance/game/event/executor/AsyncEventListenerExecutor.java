package com.lance.game.event.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lance.game.event.Event;
import com.lance.game.event.invoker.EventListenerInvoker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步事件执行器
 *
 * @author Lance
 * @since 2021/7/14
 */
public class AsyncEventListenerExecutor extends AbstractEventListenerExecutor {

    private static final int DEFAULT_CORE_SIZE = 8;

    private final ExecutorService[] executorServices;

    public AsyncEventListenerExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("async-event-executor-%d").build();

        this.executorServices = new ExecutorService[DEFAULT_CORE_SIZE];
        for (int i = 0; i < DEFAULT_CORE_SIZE; i++) {
            this.executorServices[i] = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(), threadFactory, new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    public AsyncEventListenerExecutor(ExecutorService[] executorServices) {
        this.executorServices = executorServices;
    }

    @Override
    public void invokeListener(EventListenerInvoker invoker, Event event) {
        Callable<Object> callable = () -> {
            try {
                invoker.invokeListener(event);
            } catch (Exception e) {
                getExceptionHandler().handleError(event, e);
            }
            return null;
        };

        executorServices[modValue(event.modValue())].submit(callable);
    }

    private int modValue(int id) {
        return Math.abs(id) & DEFAULT_CORE_SIZE - 1;
    }
}
