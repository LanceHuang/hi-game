package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Lance
 * @since 2021/8/31
 */
public class AttributeComputers {

    private static final Map<AttributeType, AttributeComputer> COMPUTERS;

    static {
        COMPUTERS = new ConcurrentSkipListMap<>(
                Comparator.comparing(AttributeType::getOrder).reversed().thenComparing(AttributeType::getId));

        for (AttributeType attributeType : AttributeType.values()) {
            register(attributeType, attributeType.getComputer());
        }
    }

    public static void register(AttributeType type, AttributeComputer computer) {
        COMPUTERS.put(type, computer);
    }

    public static AttributeComputer getComputer(AttributeType type) {
        return COMPUTERS.get(type);
    }

    public static Map<AttributeType, AttributeComputer> getAllComputer() {
        return COMPUTERS;
    }
}
