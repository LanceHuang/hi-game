package com.lance.game.demo.module.task.service;

import com.lance.game.demo.module.player.model.Player;

/**
 * 任务
 *
 * @author Lance
 * @since 2020年2月10日15:04:04
 */
public interface ITaskService {

    /**
     * 接受任务
     *
     * @param taskId 任务id
     */
    void acceptTask(Player player, int taskId);

    /**
     * 放弃任务
     *
     * @param taskId 任务id
     */
    void cancelTask(Player player, int taskId);

    /**
     * 领取任务奖励
     *
     * @param taskId 任务id
     */
    void receiveReward(Player player, int taskId);
}
