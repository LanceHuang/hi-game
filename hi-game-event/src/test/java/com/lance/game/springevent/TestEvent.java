package com.lance.game.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class TestEvent extends ApplicationEvent {

    public TestEvent(Object source) {
        super(source);
    }
}
