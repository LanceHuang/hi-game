package com.lance.game.demo.module.activity.task;

import com.lance.game.demo.core.log.LoggerUtil;
import com.lance.game.net.util.NamedThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 活动调度器
 * <ul>
 * <li>jstack可显示线程名，便于调试</li>
 * <li>野外战斗卡住时，不影响活动开启</li>
 * </ul>
 *
 * @author Lance
 * @since 2020/1/17 17:23
 */
@Component
public class ActivityScheduler {

    private ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void init() {
        LoggerUtil.debug("初始化活动调度器");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("Activity"));
    }

    /**
     * 调度任务
     *
     * @param name 任务名称
     * @param task 任务对象
     * @param date 运行任务时间点
     */
    public ScheduledFuture<?> schedule(String name, Runnable task, Date date) {
        return schedule(name, task, date.getTime() - System.currentTimeMillis());
    }

    /**
     * 调度任务
     *
     * @param name  任务名称
     * @param task  任务对象
     * @param delay 任务延迟
     */
    public ScheduledFuture<?> schedule(String name, Runnable task, long delay) {
        return scheduledExecutorService.schedule(wrapTask(name, task), delay, TimeUnit.MILLISECONDS);
    }

    private Runnable wrapTask(String name, Runnable task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    LoggerUtil.error("An error occurs: {}", name);
                }
            }
        };
    }

    @PreDestroy
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
