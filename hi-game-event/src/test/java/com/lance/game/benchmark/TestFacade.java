package com.lance.game.benchmark;

import com.lance.game.event.annotation.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Lance
 * @since 2022/3/28
 */
@Component
public class TestFacade {

    @EventListener
    public void syncEvent1(GameTestEvent event) {
    }

    @org.springframework.context.event.EventListener
    public void syncEvent1(SpringTestEvent event) {
    }

}
