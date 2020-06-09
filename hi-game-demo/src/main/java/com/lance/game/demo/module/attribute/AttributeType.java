package com.lance.game.demo.module.attribute;

/**
 * 属性类型
 *
 * @author Lance
 */
public enum AttributeType {

    //--------------------------- 一级属性 ---------------------------
    // todo 力量、敏捷、智慧、体力 要有个机制可以级联计算


    //--------------------------- 二级属性 ---------------------------
    /** 生命 */
    HP(1),
    /** 生命加成 */
    HP_RATE(101, false),
    /** 攻击 */
    ATK(2),
    /** 攻击加成 */
    ATK_RATE(102, false),
    /** 攻击（不享受加成） */
    ATK_FIX(202, false),
    /** 防御 */
    DEFENSE(3),
    /** 防御加成 */
    DEFENSE_RATE(103, false),

    //--------------------------- 特殊属性 ---------------------------
    // todo 不归到上述范围的属性，如：金木水火土适应性
    ;

    /** 属性id */
    private int id;

    /** 是否需要传给客户端 */
    private boolean view;

    AttributeType(int id) {
        this.id = id;
    }

    AttributeType(int id, boolean view) {
        this.id = id;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public boolean isView() {
        return view;
    }
}
