package com.lance.game.lab.event;

import javax.annotation.Resource;

/**
 * @author Lance
 */
public class SimpleEventPublisher implements EventPublisher {

    @Resource
    private EventMulticaster eventMulticaster;

    @Override
    public void publishEvent(Object event) {
        getEventMulticaster().multicastEvent(event);
    }

    public EventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }

    public void setEventMulticaster(EventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }
}
