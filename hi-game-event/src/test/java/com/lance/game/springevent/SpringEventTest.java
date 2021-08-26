package com.lance.game.springevent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StopWatch;

/**
 * @author Lance
 * @since 2021/7/14
 */
@SpringBootTest(classes = SpringEventTest.Config.class)
public class SpringEventTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    public void test() {
        // 预热
        for (int i = 0; i < 20; i++) {
            eventPublisher.publishEvent(new TestEvent(this));
        }

        // 测试
        System.out.println("Start at " + System.currentTimeMillis());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000000; i++) {
            eventPublisher.publishEvent(new TestEvent(this));
        }
        stopWatch.stop();
        System.out.println("Cost in " + stopWatch.getLastTaskTimeMillis() + " ms");
    }

    @SpringBootApplication
    @EnableAsync
    public static class Config {
    }
}