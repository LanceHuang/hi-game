package com.lance.game.springevent;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lance
 * @since 2021/7/14
 */
@Component
public class XXFacade {

    private AtomicInteger counter = new AtomicInteger();

//    @EventListener
//    public void syncEvent1(TestEvent event) {
////        System.out.println("syncEvent1 thread: " + Thread.currentThread());
//        if (counter.incrementAndGet() == 10000020) {
//            System.out.println("End at " + System.currentTimeMillis());
//        }
//    }

//    @EventListener
//    @Order
//    public void syncEvent2(TestEvent event) {
//        System.out.println("syncEvent2 thread: " + Thread.currentThread());
//    }

    @EventListener
    @Order
    @Async
    public void asyncEvent1(TestEvent event) {
//        System.out.println("asyncEvent1 thread: " + Thread.currentThread());
        if (counter.incrementAndGet() == 10000020) {
            System.out.println("End at " + System.currentTimeMillis());
        }
    }
//
//    @EventListener
//    @Async
//    public void asyncEvent2(TestEvent event) {
//        System.out.println("asyncEvent2 thread: " + Thread.currentThread());
//    }
}
