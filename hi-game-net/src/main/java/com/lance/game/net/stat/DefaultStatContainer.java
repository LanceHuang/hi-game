package com.lance.game.net.stat;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Lance
 * @since 2019/12/13 12:29
 */
public class DefaultStatContainer implements IStatContainer {

    private AtomicLong taskNum = new AtomicLong();

    private AtomicLong useTimes = new AtomicLong();

    /**
     * 一般只有业务任务会统计任务耗时，所以用get方法不会有很大的性能压力
     */
    private ConcurrentHashMap<String, TaskStat> statMap = new ConcurrentHashMap<>();

    @Override
    public void stat(String taskName, long useTime) {
        getTaskStat(taskName);
        taskNum.incrementAndGet();
        useTimes.addAndGet(useTime);
    }

    private TaskStat getTaskStat(String taskName) {
        TaskStat taskStat = statMap.get(taskName);
        if (null == taskStat) {
            taskStat = new TaskStat();
            TaskStat orgTaskStat = statMap.putIfAbsent(taskName, taskStat);
            if (orgTaskStat != null) {
                taskStat = orgTaskStat;
            }
        }
        return taskStat;
    }

    // todo
}
