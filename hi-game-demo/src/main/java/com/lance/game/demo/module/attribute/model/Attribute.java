package com.lance.game.demo.module.attribute.model;

import com.lance.game.demo.module.attribute.constant.AttributeType;
import lombok.Data;

/**
 * 属性：{"type":"ATK","value":300}
 *
 * @author Lance
 * @since 2021/8/30
 */
@Data
public class Attribute {

    /** 属性类型 */
    private AttributeType type;

    /** 属性值 */
    private long value;

    public static Attribute valueOf(AttributeType type, long value) {
        Attribute attr = new Attribute();
        attr.type = type;
        attr.value = value;
        return attr;
    }
}
