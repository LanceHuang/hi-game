package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.model.Attribute;
import com.lance.game.demo.module.attribute.operate.AttributeOperate;

/**
 * 属性类型
 *
 * @author Lance
 */
public enum AttributeType {

    /** 攻击 */
    ATK(1),
    /** 攻击加成 */
    ATK_RATE(2),
    /** 防御 */
    DEFENSE(3),
    /** 防御加成 */
    DEFENSE_RATE(4),
    /** 最大生命 */
    MAX_HP(5),
    /** 最大生命加成 */
    MAX_HP_RATE(6),
    ;

    /** 属性id */
    private final int id;

    /** 属性运算类型 */
    private final AttributeOperateType attributeOperateType;

    /** 属性效果类型 */
    private final AttributeEffectType attributeEffectType;

    /** 受影响属性 */
    private final Attribute[] effectiveAttributes;

    AttributeType(int id) {
        this.id = id;
        this.attributeOperateType = AttributeOperateType.BASIC;
        this.attributeEffectType = null;
        this.effectiveAttributes = null;
    }

    public AttributeOperate getAttributeOperate() {
        return attributeOperateType.getAttributeOperate();
    }

    //=================== Getter ===========================

    public int getId() {
        return id;
    }

    public AttributeOperateType getAttributeOperateType() {
        return attributeOperateType;
    }

    public AttributeEffectType getAttributeEffectType() {
        return attributeEffectType;
    }

    public Attribute[] getEffectiveAttributes() {
        return effectiveAttributes;
    }
}
