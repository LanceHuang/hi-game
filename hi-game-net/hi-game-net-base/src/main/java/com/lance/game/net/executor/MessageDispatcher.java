package com.lance.game.net.executor;

import com.lance.game.net.message.MessageDefinition;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.session.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消息分发器
 *
 * @author Lance
 * @since 2021/4/9
 */
@Slf4j
public class MessageDispatcher {

    /** CPU核心数 */
    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

    private final ExecutorService[] executorServices;

    public MessageDispatcher() {
        this.executorServices = new ExecutorService[CORE_SIZE];
        for (int i = 0; i < CORE_SIZE; i++) {
            // todo ThreadFactory
            this.executorServices[i] = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * 分发消息
     *
     * @param session 会话
     * @param msg     消息
     */
    public void dispatch(Session session, Object msg) {
        this.executorServices[modKey(session.getId())].submit(() -> {
            handle(session, msg);
        });
    }

    private int modKey(int key) {
        // todo
        return 0;
    }

    private int modKey(String key) {
        // todo
        return 0;
    }

    private void handle(Session session, Object msg) {
        // 判断是否有相应的处理器
        MessageDefinition messageDefinition = MessageManager.getInstance().getMessageDefinition(msg.getClass());
        if (messageDefinition == null || messageDefinition.getHandler() == null) {
            log.error("Unsupported message type: {}", msg.getClass().getName());
            return;
        }
        messageDefinition.getHandler().handle(session, msg);
    }
}
