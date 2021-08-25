package com.lance.game.stat;

import lombok.Getter;

/**
 * CPU占用率计算
 *
 * @author Lance
 * @since 2021/8/25
 */
@Getter
public class CpuStat {

    /** 1分钟CPU占用率 */
    private final CpuPeriodStat stat1 = new CpuPeriodStat(60 * 1000);
    /** 5分钟CPU占用率 */
    private final CpuPeriodStat stat5 = new CpuPeriodStat(5 * 60 * 1000);
    /** 15分钟CPU占用率 */
    private final CpuPeriodStat stat15 = new CpuPeriodStat(15 * 60 * 1000);

    /**
     * 计算占用率
     *
     * @param val 任务耗时
     */
    public void noteValue(long val) {
        this.stat1.noteValue(val);
        this.stat5.noteValue(val);
        this.stat15.noteValue(val);
    }
}
