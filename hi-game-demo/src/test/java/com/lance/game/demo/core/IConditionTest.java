package com.lance.game.demo.core;

import com.lance.game.demo.core.condition.ConditionType;
import com.lance.game.demo.core.model.ConditionDef;
import com.lance.game.demo.core.util.ConditionUtils;
import com.lance.game.demo.module.player.model.Player;
import org.junit.Test;

public class IConditionTest {

    @Test
    public void test() {
        ConditionDef conditionDef = new ConditionDef();
        conditionDef.setType(ConditionType.LEVEL);
        conditionDef.setValue("16");
        ICondition condition = ConditionUtils.parseCondition(conditionDef);

        Player mockPlayer = new Player();
        mockPlayer.setLevel(15);
        System.out.println(condition.verify(mockPlayer));
        condition.verifyThrow(mockPlayer);
    }

    @Test
    public void testAnd() {
        ConditionDef conditionDef1 = new ConditionDef();
        conditionDef1.setType(ConditionType.LEVEL);
        conditionDef1.setValue("15");
        ConditionDef conditionDef2 = new ConditionDef();
        conditionDef2.setType(ConditionType.LEVEL_RANGE);
        conditionDef2.setValue("14_14");
        ICondition condition = ConditionUtils.parseCondition(new ConditionDef[]{conditionDef1, conditionDef2});

        Player mockPlayer = new Player();
        mockPlayer.setLevel(15);
        System.out.println(condition.verify(mockPlayer));
        condition.verifyThrow(mockPlayer);
    }
}