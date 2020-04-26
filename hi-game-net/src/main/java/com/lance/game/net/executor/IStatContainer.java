package com.lance.game.net.executor;

/**
 * 统计数据容器（便于以后开发优化）
 *
 * @author Lance
 * @since 2019/12/13 11:11
 */
public interface IStatContainer {

    /**
     * 统计耗时
     *
     * @param taskName 任务名称
     * @param useTime  耗时
     */
    void stat(String taskName, long useTime);

}
