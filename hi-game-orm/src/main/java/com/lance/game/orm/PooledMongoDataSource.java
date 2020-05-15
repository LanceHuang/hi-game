package com.lance.game.orm;

import com.lance.game.orm.util.MongoUtils;
import com.mongodb.client.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认连接池
 *
 * @author Lance
 */
public class PooledMongoDataSource implements MongoDataSource, Closeable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final int DEFAULT_MAX_ACTIVE = 4;

    /** 连接池 */
    protected MongoClient[] clientPool;

    /** 最大连接数 */
    protected int maxActive = DEFAULT_MAX_ACTIVE;
    // 最大等待时间
//    protected long maxWait;

    /** 当前连接数 */
    protected int activeCount;
    /** 连接池连接数 */
    protected int pooledCount;

    protected String url;

    /** 关闭 */
    protected volatile boolean close;

    protected Lock lock = new ReentrantLock();

    @Override
    public MongoClient getMongoClient() {
        init();

        this.lock.lock();
        try {
            if (this.activeCount >= this.maxActive) {
                return null;
            }

            // 优先获取池中连接
            MongoClient rawClient;
            if (this.pooledCount > 0) {
                rawClient = this.clientPool[--this.pooledCount];
            } else {
                rawClient = MongoUtils.getClient(this.url);
            }
            if (rawClient == null) { // 异常情况
                return null;
            }

            this.activeCount++;
            logger.debug("获取MongoDB连接，最大连接数：{}，池中连接数：{}，当前连接数：{}", this.maxActive, this.pooledCount, this.activeCount);
            return new MongoClientWrapper(rawClient, this);
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 初始化连接池
     */
    public void init() {
        if (this.clientPool == null) {
            this.lock.lock();
            try {
                if (this.clientPool == null) {
                    if (this.maxActive <= 0) {
                        throw new IllegalArgumentException("maxActive必须大于0：" + this.maxActive);
                    }
                    if (this.url == null) {
                        throw new IllegalArgumentException("url不能为null");
                    }

                    this.clientPool = new MongoClient[this.maxActive];
                    logger.debug("初始化MongoDB连接池");
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (this.close) {
            return;
        }

        this.lock.lock();
        try {
            for (MongoClient rawClient : this.clientPool) {
                if (rawClient == null) {
                    continue;
                }

                MongoUtils.close(rawClient);
            }

            this.close = true;
            logger.debug("关闭MongoDB连接池");
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 回收连接
     */
    public void recycle(MongoClientWrapper mongoClientWrapper) {
        if (mongoClientWrapper == null || mongoClientWrapper.getMongoClient() == null) {
            return;
        }

        this.lock.lock();
        try {
            this.clientPool[this.pooledCount++] = mongoClientWrapper.getMongoClient();
            this.activeCount--;
            logger.debug("回收MongoDB连接，最大连接数：{}，池中连接数：{}，当前连接数：{}", this.maxActive, this.pooledCount, this.activeCount);
        } finally {
            this.lock.unlock();
        }
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
