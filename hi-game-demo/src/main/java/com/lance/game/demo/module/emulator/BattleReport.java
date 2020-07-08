package com.lance.game.demo.module.emulator;

import lombok.Data;

/**
 * 战报
 * @author Lance
 */
@Data
public class BattleReport {

    /** 战斗id */
    private long id;

    /** 随机种子 */
    private long seed;

    public BattleReport(long id, long seed) {
        this.id = id;
        this.seed = seed;
    }
}
