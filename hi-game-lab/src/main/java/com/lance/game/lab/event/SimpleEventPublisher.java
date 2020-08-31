package com.lance.game.lab.event;

/**
 * @author Lance
 */
public class SimpleEventPublisher implements EventPublisher {

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
