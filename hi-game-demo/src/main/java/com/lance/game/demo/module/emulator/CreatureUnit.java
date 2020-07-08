package com.lance.game.demo.module.emulator;

import lombok.Data;

/**
 * 生物单元
 *
 * @author Lance
 */
@Data
public class CreatureUnit {

    private long id;

    private State state;

    private long maxHp;

    private long hp;

    private long atk;

    public CreatureUnit(long id) {
        this.id = id;
        this.state = State.NORMAL;
    }

    /**
     * 判断该单元是否已阵亡
     */
    public boolean isDead() {
        return this.hp <= 0L;
    }

    /**
     * 扣血
     */
    public void reduceHp(long reduce) {
        this.hp = Math.max(0L, this.hp - reduce);
    }
}
