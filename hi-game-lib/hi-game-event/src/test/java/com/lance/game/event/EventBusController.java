package com.lance.game.event;

import org.springframework.stereotype.Controller;

/**
 * @author Lance
 */
@Controller
public class EventBusController {

    @EventListener
    public void onTestEvent(TestEvent event) {
        System.out.println(Thread.currentThread() + ": Receive TestEvent");
    }
}
