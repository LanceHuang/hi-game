package com.lance.game.event;

import javax.annotation.Resource;

/**
 * 遍历所有的监听者，同步执行任务
 *
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
