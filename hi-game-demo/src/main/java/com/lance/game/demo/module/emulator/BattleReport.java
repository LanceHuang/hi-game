package com.lance.game.demo.module.emulator;

import lombok.Data;

/**
 * 战报
 *
 * @author Lance
 */
@Data
public class BattleReport {

    /** 战斗id */
    private long id;

    /** 随机种子 */
    private long seed;

    /** 战斗结果 */
    private int result;

    // todo 记录战斗流程：快照+行为+变化属性

}