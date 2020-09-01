package com.lance.game.lab.event;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * 遍历所有的监听者。若提供了任务执行线程，则异步执行任务；否则同步执行任务
 *
 * @author Lance
 */
public class TaskEventMulticaster implements EventMulticaster {

    @Resource
    private EventContext eventContext;

    /** 任务执行线程 */
    private Executor taskExecutor;

    @Override
    public void multicastEvent(Object event) {
        for (final EventHandler eventHandler : eventContext.getEventHandler(event.getClass())) {
            Executor executor = getTaskExecutor();
            if (executor != null) {
                executor.execute(() -> eventHandler.onEvent(event));
            } else {
                eventHandler.onEvent(event);
            }
        }
    }

    public Executor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}