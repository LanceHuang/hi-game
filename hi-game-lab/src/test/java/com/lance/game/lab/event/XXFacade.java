package com.lance.game.lab.event;

import com.lance.game.lab.event.annotation.Async;
import com.lance.game.lab.event.annotation.Filter;
import com.lance.game.lab.event.annotation.EventListener;
import com.lance.game.lab.event.annotation.FilterType;
import com.lance.game.lab.event.annotation.Order;
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
