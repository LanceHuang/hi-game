package com.lance.game.event;

import com.lance.game.event.config.EventConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.StopWatch;

/**
 * @author Lance
 * @since 2021/7/14
 */
@SpringBootTest(classes = EventTest.Config.class)
public class EventTest {

    @Autowired
    private EventBus eventBus;

    @Test
    public void test() {
        // 预热
        for (int i = 0; i < 20; i++) {
            eventBus.publishEvent(new TestEvent(this));
        }

        // 测试
        System.out.println("Start at " + System.currentTimeMillis());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        for (int i = 0; i < 10000000; i++) {
//            eventBus.publishEvent(new TestEvent(this));
//        }
        stopWatch.stop();
        System.out.println("Cost in " + stopWatch.getLastTaskTimeMillis() + " ms");
    }

    @SpringBootApplication
    @Import(EventConfiguration.class)
    public static class Config {
    }
}