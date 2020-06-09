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
        Map<AttributeType, Long> attributeMap = new HashMap<>();
        attributeMap.put(AttributeType.ATK, 200L);
        attributeMap.put(AttributeType.ATK_RATE, 2000L);
        attributeMap.put(AttributeType.DEFENSE, 100L);
        attributeMap.put(AttributeType.HP, 1000L);

        AttributeContainer attributeContainer = new AttributeContainer();
        attributeContainer.putModelAttributes(ModelAttributeId.TEST, attributeMap);
        attributeContainer.calculate();
        attributeContainer.printLog();
    }
}