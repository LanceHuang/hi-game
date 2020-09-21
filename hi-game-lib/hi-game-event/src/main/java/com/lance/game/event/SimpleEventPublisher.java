package com.lance.game.event;

/**
 * @author Lance
 */
public class SimpleEventPublisher implements EventPublisher {

    private EventMulticaster eventMulticaster;

    @Override
    public void publishEvent(Object event) {
        this.eventMulticaster.multicastEvent(event);
    }

    public void setEventMulticaster(EventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }
}
