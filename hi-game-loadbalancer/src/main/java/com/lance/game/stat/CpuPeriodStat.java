package com.lance.game.stat;

import lombok.Getter;

/**
 * 周期CPU占用率计算，参考netflix的Distribution
 *
 * @author Lance
 * @since 2021/8/25
 */
@Getter
public class CpuPeriodStat {

    /** 开始时间戳 */
    private final long startTime;
    /** 周期 */
    private final long period;

    /** 周期任务数 */
    private int periodNum;
    /** 周期耗时 */
    private double periodValue;

    /** 当前轮次 */
    private int round;
    /** 当前cpu占用率 */
    private double per;

    public CpuPeriodStat(long period) {
        this.startTime = System.currentTimeMillis();
        this.period = period;

        if (period <= 0) {
            throw new IllegalArgumentException("period cannot be negative");
        }
    }

    /**
     * 计算占用率
     *
     * @param val 任务耗时
     */
    public void noteValue(long val) {
        updateRound();

        this.periodNum++;
        this.periodValue += val;
    }

    private void updateRound() {
        long now = System.currentTimeMillis();
        int currRound = (int) ((now - startTime) / period);
        if (currRound == round) {
            per = periodValue / period;
        } else if (currRound > round) {
            round = currRound;
            periodNum = 0;
            periodValue = 0;
        }
    }
}
