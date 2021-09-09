package com.lance.game.lab.mud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Lance
 * @since 2021/9/7
 */
@Component
public class MudContext {

    private static MudContext instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static MudContext getInstance() {
        return instance;
    }

    //---------------------------------------------------------------------

    @Autowired
    private ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }

    @Autowired
    private MudManager mudManager;

    public static MudManager getMudManager() {
        return instance.mudManager;
    }
}
