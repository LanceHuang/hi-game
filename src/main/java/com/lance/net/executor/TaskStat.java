package com.lance.net.executor;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 任务统计数据
 *
 * @author Lance
 * @since 2019/12/13 12:30
 */
public class TaskStat {

    private AtomicLong num  = new AtomicLong();
    private AtomicLong time = new AtomicLong();

}
