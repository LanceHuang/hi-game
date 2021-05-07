package com.lance.game.net.executor;

import com.lance.game.net.session.Session;

/**
 * 游戏任务执行器
 *
 * @author Lance
 * @since 2019/12/12 18:09
 */
public interface IGameExecutor {

    /**
     * 添加会话任务（之所以用IGameTask而不用Runnable，是因为在IGameExecutor中需要添加任务耗时统计等逻辑。直接调用task.run()，然后在运行前后记录时间，所以没必要用Runnable）
     *
     * @param session 会话对象
     * @param task    任务
     */
    void addSessionTask(Session session, Runnable task);

    /**
     * 添加任务（任务耗时统计，按账号绑定线程）
     *
     * @param identify 标识
     * @param taskName 任务名称
     * @param task     任务
     */
    void addIdentifyTask(String identify, String taskName, Runnable task);

    /**
     * 添加任务（任务耗时统计，Round-Robin算法）
     *
     * @param taskName 任务名称
     * @param task     任务
     */
    void addTask(String taskName, Runnable task);

    /**
     * 关闭执行器
     */
    void shutdown();

    /**
     * 查看运行的线程数
     */
    int getTaskSize();
}
