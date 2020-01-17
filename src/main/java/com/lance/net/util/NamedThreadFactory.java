package com.lance.net.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 命名线程工厂（项目中可能会有大量的线程池，定义名称后便于jstack工具调试）
 *
 * @author Lance
 * @since 2019/12/13 15:36
 */
public class NamedThreadFactory implements ThreadFactory {

    private String prefix;

    private AtomicInteger index = new AtomicInteger();

    public NamedThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix + "-" + index.getAndIncrement());
    }
}
