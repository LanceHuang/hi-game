package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.model.Attribute;

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
    /** 生命 */
    HP(5),
    /** 生命加成 */
    HP_RATE(6),
    /** 武器攻击加成 */
    WEAPON_ATK_RATE(7),
    ;

    /** 属性id */
    private int id;

    /** 属性更新类型 */
    private AttributeUpdateType attributeUpdateType;

    /** 属性效果类型 */
    private AttributeEffectType attributeEffectType;

    /** 受影响属性 */
    private Attribute[] effectiveAttributes;

    AttributeType(int id) {
        this.id = id;
    }

    /**
     * 判断是否有更新操作
     */
    public boolean hasUpdate() {
        return attributeUpdateType != null;
    }

    /**
     * 判断是否有属性效果
     */
    public boolean hasEffect() {
        return attributeEffectType != null;
    }

    //=================== Getter ===========================

    public int getId() {
        return id;
    }

    public AttributeUpdateType getAttributeUpdateType() {
        return attributeUpdateType;
    }

    public AttributeEffectType getAttributeComputerType() {
        return attributeEffectType;
    }

    public Attribute[] getEffectiveAttributes() {
        return effectiveAttributes;
    }
}
