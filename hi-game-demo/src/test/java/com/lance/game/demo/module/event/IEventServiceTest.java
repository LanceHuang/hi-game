package com.lance.game.demo.module.event;

import com.lance.game.demo.module.event.model.TestEvent;
import com.lance.game.demo.module.event.service.IEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Lance
 * @since 2021/4/25
 */
@SpringBootTest(classes = IEventServiceTest.Config.class)
public class IEventServiceTest {

    @Autowired
    private IEventService eventService;

    @Test
    public void test() throws InterruptedException {
        System.out.println(Thread.currentThread() + "发布事件");
        eventService.publishEvent(new TestEvent());

        Thread.sleep(3000L);
    }

    @SpringBootApplication
    @EnableAsync
    public static class Config {
    }
}