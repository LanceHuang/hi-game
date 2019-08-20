package com.lance.game.module.chat.service;

import com.lance.game.module.chat.model.Message;
import com.lance.game.module.player.model.Player;

/**
 * 聊天
 *
 * @author Lance
 * @since 2019/8/9 17:44
 */
public interface IChatService {

    /**
     * 私聊
     */
    void privateChat(Player player, String account, Message message);
}
