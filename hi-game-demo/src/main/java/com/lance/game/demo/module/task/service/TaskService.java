package com.lance.game.demo.module.task.service;

import com.lance.game.demo.module.player.model.Player;
import org.springframework.stereotype.Service;

/**
 * @author Lance
 * @since 2020年2月10日15:11:11
 */
@Service
public class TaskService implements ITaskService {

    @Override
    public void acceptTask(Player player, int taskId) {

    }

    @Override
    public void cancelTask(Player player, int taskId) {

    }

    @Override
    public void receiveReward(Player player, int taskId) {

    }
}
