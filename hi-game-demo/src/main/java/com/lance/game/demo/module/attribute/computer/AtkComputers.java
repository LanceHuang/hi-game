package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.constant.AttributeType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Lance
 * @since 2021/8/31
 */
public class AtkComputers {

    private static final Map<AttributeType, AttributeComputer> COMPUTERS = new ConcurrentSkipListMap<>();

    static {
        register(new AtkComputer());
    }

    public static void register(AttributeComputer computer) {
        COMPUTERS.put(computer.getAttributeType(), computer);
    }

    public static void register(AttributeType type, AttributeComputer computer) {
        COMPUTERS.put(type, computer);
    }

    public static AttributeComputer getComputer(AttributeType type) {
        return COMPUTERS.get(type);
    }

    public static Collection<AttributeComputer> getAllComputer() {
        return COMPUTERS.values();
    }
}
