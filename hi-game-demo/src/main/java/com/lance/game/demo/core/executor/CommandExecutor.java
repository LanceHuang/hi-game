package com.lance.game.demo.core.executor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lance
 */
@Component
public class CommandExecutor implements ICommandExecutor {

    /** 工作线程池大小 */
    private int threadPoolSize;

    /** 工作线程池 */
    private ExecutorService[] executors;

    @PostConstruct
    @Override
    public void init() {
        int threadPoolSize = Runtime.getRuntime().availableProcessors();
        this.executors = new ExecutorService[threadPoolSize];
        for (int i = 0; i < threadPoolSize; i++) {
            this.executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    @Override
    public void submit(String key, Runnable task) {
        int index = key.hashCode() % this.threadPoolSize;
        // 不需要future，故直接用execute
        this.executors[index].execute(task);
    }

    @Override
    public void stop() {
        if (this.executors == null) {
            return;
        }

        for (int i = 0; i < this.threadPoolSize; i++) {
            if (this.executors[i] == null) {
                continue;
            }
            this.executors[i].shutdown();
        }

        // TODO: 2020/5/8 等待线程池完成所有任务
    }
}
