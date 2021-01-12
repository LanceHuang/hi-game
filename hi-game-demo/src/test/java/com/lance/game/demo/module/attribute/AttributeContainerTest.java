package com.lance.game.demo.module.attribute;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import com.lance.game.demo.module.attribute.model.AttributeContainer;
import com.lance.game.demo.module.attribute.model.AttributeId;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = AttributeContainerTest.class)
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
        attributeContainer.putAndComputeAttributes(AttributeId.TEST, testAttributeMap);
        attributeContainer.putAndComputeAttributes(AttributeId.EQUIP, equipAttributeMap);
    }
}