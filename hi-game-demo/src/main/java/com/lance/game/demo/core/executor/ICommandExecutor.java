package com.lance.game.demo.core.executor;

/**
 * 命令执行器
 *
 * @author Lance
 */
public interface ICommandExecutor {

    /**
     * 初始化线程池
     */
    void init();

    /**
     * 提交任务
     *
     * @param key  任务主键
     * @param task 任务
     */
    void submit(String key, Runnable task);

    /**
     * 关闭线程池
     */
    void stop();
}
