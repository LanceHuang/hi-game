package com.lance.game.lab.event;

import com.lance.game.lab.event.annotation.Async;
import com.lance.game.lab.event.annotation.EventListener;
import com.lance.game.lab.event.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Lance
 * @since 2021/7/14
 */
@Component
public class XXFacade {

    @EventListener
    public void syncEvent1(TestEvent event) {

    }

    @EventListener
    @Order
    public void syncEvent2(TestEvent event) {

    }

    @EventListener
    @Async
    public void asyncEvent1(TestEvent event) {

    }

    @EventListener
    @Order
    @Async
    public void asyncEvent2(TestEvent event) {

    }
}
