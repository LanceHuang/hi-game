package com.lance.game.demo.module.attribute.computer;

import com.lance.game.demo.module.attribute.AttributeType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 */
public abstract class AbstractAttributeComputer implements IAttributeComputer {

    /** 属性计算器映射表 */
    private static Map<AttributeType, IAttributeComputer> attributeComputers = new HashMap<>();

    /**
     * 初始化注册
     */
    @PostConstruct
    public void init() {
        if (getAttributeType() != null) {
            attributeComputers.put(getAttributeType(), this);
        } else {
            throw new IllegalStateException("属性类型不能为空，class=" + this.getClass());
        }
    }

    public static Map<AttributeType, IAttributeComputer> getAttributeComputers() {
        return attributeComputers;
    }
}
