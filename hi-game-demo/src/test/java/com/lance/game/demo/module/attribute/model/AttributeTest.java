package com.lance.game.demo.module.attribute.model;

import com.lance.common.tool.JsonUtils;
import com.lance.game.demo.module.attribute.constant.AttributeType;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/8/30
 */
public class AttributeTest {

    @Test
    public void test() {
        Attribute attribute = Attribute.valueOf(AttributeType.ATK, 100);
        System.out.println(attribute);

        String attributeJson = JsonUtils.object2json(attribute);
        System.out.println(attributeJson);

        Attribute newAttribute = JsonUtils.json2object(attributeJson, Attribute.class);
        System.out.println(newAttribute);
    }
}