package com.lance.game.event;

/**
 * 遍历所有的监听者，同步执行任务
 *
 * @author Lance
 */
public class SimpleEventMulticaster implements EventMulticaster {

    private EventContext eventContext;

    public SimpleEventMulticaster(EventContext eventContext) {
        this.eventContext = eventContext;
    }

    @Override
    public void multicastEvent(Object event) {
        for (EventHandler eventHandler : eventContext.getEventHandler(event.getClass())) {
            eventHandler.onEvent(event);
        }
    }

}
