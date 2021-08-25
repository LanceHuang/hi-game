package com.lance.game.stat;

import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/8/25
 */
public class LoadStatTest {

    @Test
    public void test() throws InterruptedException {
        LoadStat loadStat = new LoadStat();
        int n = 564; // 队列长度
        for (int i = 0; i < 200; i++) {
            Thread.sleep(1000L);

            loadStat.calcLoad(n);
            if (n - 10 > 0) { // 模拟任务被处理完
                n -= 10;
            }
            System.out.printf("load average %d: %3.2f %3.2f %3.2f %d\n",
                    i, loadStat.getLoad1(), loadStat.getLoad5(), loadStat.getLoad15(), n);
        }
    }
}