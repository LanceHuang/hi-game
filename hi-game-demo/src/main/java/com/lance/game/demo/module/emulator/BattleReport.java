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

    // todo 2020年8月13日11:57:25 还有一种做法，就是记录战斗双方的属性+技能，记录随机种子，还有玩家操作序列，这样可大大地节省空间
}
