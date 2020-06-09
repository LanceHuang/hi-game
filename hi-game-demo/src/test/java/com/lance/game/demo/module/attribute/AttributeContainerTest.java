package com.lance.game.demo.module.attribute;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AttributeContainerTest.class)
@ComponentScan("com.lance.game.demo.module.attribute")
public class AttributeContainerTest {

    @Test
    public void test() {
        Map<AttributeType, Long> testAttributeMap = new HashMap<>();
        testAttributeMap.put(AttributeType.ATK, 200L);
        testAttributeMap.put(AttributeType.ATK_RATE, 2000L);
        testAttributeMap.put(AttributeType.DEFENSE, 100L);
        testAttributeMap.put(AttributeType.HP, 1000L);

        Map<AttributeType, Long> equipAttributeMap = new HashMap<>();
        equipAttributeMap.put(AttributeType.ATK, 300L);
        equipAttributeMap.put(AttributeType.DEFENSE, 550L);
        equipAttributeMap.put(AttributeType.HP, 5000L);

        AttributeContainer attributeContainer = new AttributeContainer();
        attributeContainer.putAttributes(ModuleAttributeId.TEST, testAttributeMap);
        attributeContainer.putAttributes(ModuleAttributeId.EQUIP, equipAttributeMap);
        attributeContainer.recompute();
        attributeContainer.logAttributes();

        attributeContainer.clear();
        attributeContainer.logAttributes();
    }
}