package com.lance.game.lab.event;

import javax.annotation.Resource;

/**
 * 遍历所有的监听者，同步执行任务
 *
 * @author Lance
 */
public class SimpleEventMulticaster implements EventMulticaster {

    // todo 没地方注入
    @Resource
    private EventContext eventContext;

    @Override
    public void multicastEvent(Object event) {
        for (EventHandler eventHandler : eventContext.getEventHandler(event.getClass())) {
            eventHandler.onEvent(event);
        }
    }

}
