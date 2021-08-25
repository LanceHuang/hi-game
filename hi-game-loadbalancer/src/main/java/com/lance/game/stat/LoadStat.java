package com.lance.game.stat;

import lombok.Getter;

/**
 * 负载计算，参考linux的w，5秒计算一次
 *
 * @author Lance
 * @since 2021/8/25
 */
@Getter
public class LoadStat {

    private static final double EXP_1 = 1 / Math.exp(5.0 / 60);
    private static final double EXP_5 = 1 / Math.exp(5.0 / 300);
    private static final double EXP_15 = 1 / Math.exp(5.0 / 900);

    /** 1分钟负载 */
    private double load1;
    /** 5分钟负载 */
    private double load5;
    /** 15分钟负载 */
    private double load15;

    /**
     * 计算负载
     *
     * @param n 队列长度
     */
    public void calcLoad(int n) {
        n = Math.max(n, 0);
        this.load1 = calcLoad0(this.load1, EXP_1, n);
        this.load5 = calcLoad0(this.load5, EXP_5, n);
        this.load15 = calcLoad0(this.load15, EXP_15, n);
    }

    private double calcLoad0(double load, double exp, int n) {
        return load * exp + n * (1 - exp);
    }
}
