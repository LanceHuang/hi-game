package com.lance.net.executor;

import com.lance.net.Session;
import com.lance.net.util.NamedThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认游戏任务执行器，为了降耦，这里不调用spring相关的内容
 *
 * @author Lance
 * @since 2019/12/13 9:51
 */
public class DefaultGameExecutor implements IGameExecutor {

    private int DEFAULT_PROCESSOR_SIZE = Runtime.getRuntime().availableProcessors();

    private ThreadPoolExecutor[] services = new ThreadPoolExecutor[DEFAULT_PROCESSOR_SIZE];

    private AtomicInteger index = new AtomicInteger();

    private IStatContainer statContainer;

    private Class<? extends IStatContainer> statContainerClass;

    public DefaultGameExecutor() {
        ThreadFactory threadFactory = new NamedThreadFactory("core");
        for (int i = 0; i < DEFAULT_PROCESSOR_SIZE; i++) {
            services[i] = new ThreadPoolExecutor(1, 1,);
            // todo 自定义？
        }

    }

    @Override
    public void addSessionTask(Session session, Runnable task) {
        Object identify = session.getAttribute(Session.KEY_IDENTIFY);
        if (null == identify) {
            // todo login
        } else {
            services[identify.hashCode() % DEFAULT_PROCESSOR_SIZE].execute(task);
        }
    }

    @Override
    public void addIdentifyTask(String identify, final String taskName, final Runnable task) {
        services[identify.hashCode() % DEFAULT_PROCESSOR_SIZE].execute(new Runnable() {
            @Override
            public void run() {
                long t1 = System.currentTimeMillis();
                task.run();
                long t2 = System.currentTimeMillis();
                getStatContainer().stat(taskName, t2 - t1);
            }
        });
    }

    @Override
    public void addTask(String taskName, Runnable task) {
        services[next()].execute(new Runnable() {
            @Override
            public void run() {
                long t1 = System.currentTimeMillis();
                try {
                    task.run();
                } catch (Exception e) {
                    // todo
                } finally {
                    long t2 = System.currentTimeMillis();
                    getStatContainer().stat(taskName, t2 - t1);
                }
            }
        });
    }

    /**
     * CPU核心数必然是2的幂
     */
    private int next() {
        return index.getAndIncrement() & DEFAULT_PROCESSOR_SIZE - 1;
    }

    @Override
    public void shutdown() {
        for (int i = 0; i < DEFAULT_PROCESSOR_SIZE; i++) {
            services[i].shutdown();
        }
    }

    public IStatContainer getStatContainer() {
        if (null == statContainer) {
            synchronized (this) {
                if (null == statContainer) {
                    if (null == statContainerClass) {
                        throw new IllegalStateException("statContainerClass can not be null");
                    }

                    try {
                        statContainer = statContainerClass.newInstance();
                    } catch (Exception e) {
                        throw new IllegalStateException("statContainerClass can not be instantiate", e);
                    }
                }
            }
        }
        return statContainer;
    }

    @Override
    public int getTaskSize() {
        // todo
        return 0;
    }
}
