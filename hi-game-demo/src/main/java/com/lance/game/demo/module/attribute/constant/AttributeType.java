package com.lance.game.demo.module.attribute.constant;

import com.lance.game.demo.module.attribute.computer.AtkComputer;
import com.lance.game.demo.module.attribute.computer.AttributeComputer;
import com.lance.game.demo.module.attribute.computer.DefaultAttributeComputer;
import com.lance.game.demo.module.attribute.operator.AttributeOperator;
import com.lance.game.demo.module.attribute.operator.DefaultAttributeOperator;

/**
 * 属性类型
 *
 * @author Lance
 * @since 2021/9/2
 */
public enum AttributeType {

    //---------------------------------------- 基础属性 ------------------------------------------------
    /** 攻击力 */
    ATK(1, new AtkComputer()),
    /** 攻击力加成 */
    ATK_RATE(2),
    /** 防御力 */
    DEFENSE(3),
    /** 防御力加成 */
    DEFENSE_RATE(4),
    /** 最大生命值 */
    MAX_HP(5),
    /** 最大生命值加成 */
    MAX_HP_RATE(6),

    //---------------------------------------- 进阶属性 ------------------------------------------------
    /** 暴击率 */
    CRITICAL_RATE(101),
    /** 暴击伤害 */
    CRITICAL_DAMAGE(102),

    //---------------------------------------- 元素属性 ------------------------------------------------

    ;

    /** 属性id */
    private final int id;

    /** 属性运算器 */
    private AttributeOperator operator = DefaultAttributeOperator.getInstance();

    /** 属性计算顺序 */
    private int order = AttributeConstant.MIN_COMPUTE_ORDER;

    /** 属性计算器 */
    private AttributeComputer computer = DefaultAttributeComputer.getInstance();

    AttributeType(int id) {
        this.id = id;
    }

    AttributeType(int id, AttributeOperator operator) {
        this.id = id;
        this.operator = operator;
    }

    AttributeType(int id, AttributeComputer computer) {
        this.id = id;
        this.computer = computer;
    }

    AttributeType(int id, AttributeOperator operator, int order, AttributeComputer computer) {
        this.id = id;
        this.operator = operator;
        this.order = order;
        this.computer = computer;
    }

    public static AttributeType valueOf(int id) {
        for (AttributeType attributeType : values()) {
            if (attributeType.id == id) {
                return attributeType;
            }
        }
        return null;
    }

    //=================== Getter ===========================

    public int getId() {
        return id;
    }

    public AttributeOperator getOperator() {
        return operator;
    }

    public int getOrder() {
        return order;
    }

    public AttributeComputer getComputer() {
        return computer;
    }


}
