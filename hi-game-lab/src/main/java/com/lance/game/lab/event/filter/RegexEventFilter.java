package com.lance.game.lab.event.filter;

import com.lance.game.lab.event.annotation.EventListener;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author Lance
 * @since 2021/7/15
 */
public class RegexEventFilter implements EventFilter {

    private final Pattern[] patterns;

    public RegexEventFilter(Object bean, Method method) {
        EventListener eventListener = method.getAnnotation(EventListener.class);
        String[] pattenStrings = eventListener.pattern();
        if (pattenStrings.length == 0) {
            throw new IllegalArgumentException("EventListener.pattern cannot be empty: " + bean.getClass().getName() + "#" + method.getName());
        } else {
            this.patterns = new Pattern[pattenStrings.length];
            for (int i = 0; i < pattenStrings.length; i++) {
                String p = pattenStrings[i].trim().replaceAll("[.]", "[.]").replaceAll("[*]", "\\\\w+");
                this.patterns[i] = Pattern.compile(p);
            }
        }
    }

    @Override
    public boolean match(Class<?> eventType) {
        for (Pattern pattern : patterns) {
            if (pattern.matcher(eventType.getName()).matches()) {
                return true;
            }
        }
        return false;
    }
}
