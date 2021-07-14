package com.lance.game.lab.event.filter;

import com.lance.game.lab.event.Event;

/**
 * @author Lance
 * @since 2021/7/15
 */
public interface EventFilter {

    boolean match(Event event);
}
