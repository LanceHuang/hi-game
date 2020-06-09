package com.lance.game.demo.module.attribute.formula;

import com.lance.game.demo.module.attribute.AttributeType;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 */
public abstract class AbstractAttributeFormula implements IAttributeFormula {

    /** 属性公式映射表 */
    private final static Map<AttributeType, IAttributeFormula> attributeFormulas = new HashMap<>();

    /**
     * 初始化注册
     */
    @PostConstruct
    public void init() {
        if (getAttributeType() != null) {
            attributeFormulas.put(getAttributeType(), this);
        } else {
            throw new IllegalStateException("属性类型不能为空，class=" + this.getClass());
        }
    }

    /**
     * 获取所有属性公式映射表
     */
    public static Map<AttributeType, IAttributeFormula> getAttributeFormulas() {
        return attributeFormulas;
    }
}
