package com.lance.game.demo.module.event.controller;

import com.lance.game.demo.module.event.model.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Lance
 * @since 2021/4/25
 */
@Component
public class TestController {

    @EventListener
    @Async
    @Order
    public void onTestEvent1(TestEvent event) {
        System.out.println(Thread.currentThread() + "接收事件1：" + event);
    }

    @EventListener
    @Async
    @Order(0)
    public void onTestEvent2(TestEvent event) {
        System.out.println(Thread.currentThread() + "接收事件2：" + event);
    }

    @EventListener
    @Order(0)
    public void onTestEvent3(TestEvent event) {
        System.out.println(Thread.currentThread() + "接收事件3：" + event);
    }

    @EventListener
    @Order
    public void onTestEvent4(TestEvent event) {
        System.out.println(Thread.currentThread() + "接收事件4：" + event);
    }
}
