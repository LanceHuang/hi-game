package com.lance.game.lab.snowflake;

/**
 * 雪花算法生成id，线程安全，参考 {@code https://blog.csdn.net/qq_39135287/article/details/88964572/}
 * <pre>
 *  1位符号位 + 41位timestamp + 10位机器id + 12位序列号
 * </pre>
 *
 * @author Lance
 * @since 2021/4/6
 */
public class SnowFlakeIdGenerator {

    private static final int SEQUENCE_BITS = 12;

    private static final int WORKER_ID_BITS = 10;

    private static final int TIMESTAMP_BITS = 41;

    private static final int WORKER_ID_SHIFT = SEQUENCE_BITS;

    private static final int TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    private static final int MAX_SEQUENCE = (1 << SEQUENCE_BITS) - 1;

    private static final int MAX_WORKER_ID = (1 << WORKER_ID_BITS) - 1;

    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long START_EPOCH = 1489111610226L;

    /**
     * 机器id
     */
    private int workerId;

    /**
     * 上次的时间戳
     */
    private long lastTimestamp;

    /**
     * 序列号
     */
    private int sequence;

    public SnowFlakeIdGenerator(int workerId) {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("Illegal workerId: " + workerId);
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        // 时钟回退
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException("System clock moved backward, lastTimestamp=" + this.lastTimestamp + ", timestamp=" + timestamp);
        }

        // 毫秒并发
        if (timestamp == this.lastTimestamp) {
            this.sequence = (this.sequence + 1) & MAX_SEQUENCE;
            if (this.sequence == 0L) {
                timestamp = tilNextMillis(timestamp);
                this.lastTimestamp = timestamp;
            }
        } else {
            this.lastTimestamp = timestamp;
            this.sequence = 0;
        }

        return (timestamp - START_EPOCH) << TIMESTAMP_SHIFT
                | this.workerId << WORKER_ID_SHIFT
                | this.sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
