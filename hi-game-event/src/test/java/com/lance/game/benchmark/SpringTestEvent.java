package com.lance.game.benchmark;

import org.springframework.context.ApplicationEvent;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class SpringTestEvent extends ApplicationEvent {

    public SpringTestEvent(Object source) {
        super(source);
    }
}
