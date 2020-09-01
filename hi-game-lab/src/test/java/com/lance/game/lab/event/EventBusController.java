package com.lance.game.lab.event;

import org.springframework.stereotype.Controller;

/**
 * @author Lance
 */
@Controller
public class EventBusController {

    @EventListener
    public void onTestEvent(TestEvent event) {
        System.out.println("Receive TestEvent");
        System.out.println(Thread.currentThread());
    }
}
