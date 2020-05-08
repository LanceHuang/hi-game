package com.lance.game.demo.module.chess.config;

/**
 * 棋子克制关系
 *
 * @author Lance
 */
public class ChessRestraintConfig {

    /** 类型 */
    private int type;
    /** 克制类型 */
    private int restraintType;
    /** 最终伤害加成（万分比） */
    private int addDamage;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRestraintType() {
        return restraintType;
    }

    public void setRestraintType(int restraintType) {
        this.restraintType = restraintType;
    }

    public int getAddDamage() {
        return addDamage;
    }

    public void setAddDamage(int addDamage) {
        this.addDamage = addDamage;
    }
}
