package com.lance.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GameContext.class)
public class GameContextTest {

    @Test
    public void getBean() {
        GameContext ctx = GameContext.getBean(GameContext.class);
        System.out.println(ctx);
    }

}
