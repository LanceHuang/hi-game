package com.lance.game.lab.snowflake;

import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/4/6
 */
public class SnowFlakeIdGeneratorTest {

    @Test
    public void test() {
        // 测试3秒内，毫秒并发
        SnowFlakeIdGenerator generator = new SnowFlakeIdGenerator(101);
        long curr = System.currentTimeMillis();
        long end = curr + 3000L;
        while (System.currentTimeMillis() < end) {
            generator.nextId();
        }
    }
}