package com.lance.game.demo.module.chess.config;

/**
 * 棋子
 *
 * @author Lance
 */
public class ChessConfig {

    private int id;
    /** 类型 */
    private int type;
    /** 星级 */
    private int star;
    /** 血量 */
    private long hp;
    /** 攻击力 */
    private long atk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public long getAtk() {
        return atk;
    }

    public void setAtk(long atk) {
        this.atk = atk;
    }
}
