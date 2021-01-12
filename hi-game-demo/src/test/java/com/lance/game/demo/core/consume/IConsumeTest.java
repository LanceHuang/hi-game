package com.lance.game.demo.core.consume;

import com.lance.game.demo.module.player.model.Player;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 */
class IConsumeTest {

    @Test
    void test() {
        ConsumeDef consumeDef = new ConsumeDef();
        consumeDef.setType(ConsumeType.ITEM);
        consumeDef.setValue("10086:1");
        IConsume consume = ConsumeUtils.parseConsume(consumeDef);

        Player mockPlayer = new Player();
        System.out.println(consume.verify(mockPlayer));
        consume.verifyAndConsume(mockPlayer);
    }

    @Test
    void testAnd() {
        ConsumeDef consumeDef1 = new ConsumeDef();
        consumeDef1.setType(ConsumeType.ITEM);
        consumeDef1.setValue("10086:1");
        ConsumeDef consumeDef2 = new ConsumeDef();
        consumeDef2.setType(ConsumeType.ITEM);
        consumeDef2.setValue("10087:1");
        IConsume consume = ConsumeUtils.parseConsume(new ConsumeDef[]{consumeDef1, consumeDef2});

        Player mockPlayer = new Player();
        System.out.println(consume.verify(mockPlayer));
        consume.verifyAndConsume(mockPlayer);
    }
}