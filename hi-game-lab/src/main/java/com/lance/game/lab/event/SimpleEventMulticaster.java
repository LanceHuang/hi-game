package com.lance.game.lab.event;

import javax.annotation.Resource;

/**
 * @author Lance
 */
public class SimpleEventMulticaster implements EventMulticaster {

    @Resource
    private EventContext eventContext;

    @Override
    public void multicastEvent(Object event) {
        for (EventHandler eventHandler : eventContext.getEventHandler(event.getClass())) {
            eventHandler.onEvent(event);
        }
    }

}
