package com.lance.game.lab.event;

import com.lance.game.lab.event.executor.EventListenerExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class EventListenerInvokerFactory {

    @Autowired
    private Map<String, EventListenerExecutor> executorMap = new HashMap<>();

    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method) {


        return null;
    }
}
