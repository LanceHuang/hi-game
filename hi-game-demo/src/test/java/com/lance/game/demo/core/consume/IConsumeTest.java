package com.lance.game.demo.core.consume;

import com.lance.game.demo.core.constant.ConsumeType;
import com.lance.game.demo.core.model.ConsumeDef;
import com.lance.game.demo.core.util.CoreUtils;
import com.lance.game.demo.module.player.model.Player;
import org.junit.Test;

public class IConsumeTest {

    @Test
    public void test() {
        ConsumeDef consumeDef = new ConsumeDef();
        consumeDef.setType(ConsumeType.ITEM);
        consumeDef.setValue("10086:1");
        IConsume consume = CoreUtils.parseConsume(consumeDef);

        Player mockPlayer = new Player();
        System.out.println(consume.verify(mockPlayer));
        consume.verifyAndConsume(mockPlayer);
    }

    @Test
    public void testAnd() {
        ConsumeDef consumeDef1 = new ConsumeDef();
        consumeDef1.setType(ConsumeType.ITEM);
        consumeDef1.setValue("10086:1");
        ConsumeDef consumeDef2 = new ConsumeDef();
        consumeDef2.setType(ConsumeType.GOLD);
        consumeDef2.setValue("250");
        IConsume consume = CoreUtils.parseConsume(new ConsumeDef[]{consumeDef1, consumeDef2});

        Player mockPlayer = new Player();
        System.out.println(consume.verify(mockPlayer));
        consume.verifyAndConsume(mockPlayer);
    }
}