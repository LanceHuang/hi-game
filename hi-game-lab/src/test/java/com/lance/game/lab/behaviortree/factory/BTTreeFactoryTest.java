package com.lance.game.lab.behaviortree.factory;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BehaviorTree;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2021/6/8
 */
public class BTTreeFactoryTest {

    @Test
    public void test() throws Exception {
        BehaviorTree tree = BTTreeFactory.createTree("army.bt");
        BTContext context = new BTContext();

        // 1秒触发一次
        long delay = 1000L;
        long period = 1000L;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            // log
            System.out.println("==================== tick ====================");

            // execute
            tree.execute(context);
        }, delay, period, TimeUnit.MILLISECONDS);

        // wait
        Thread.sleep(10000L);
    }

}