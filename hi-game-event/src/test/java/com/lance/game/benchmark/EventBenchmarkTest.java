package com.lance.game.benchmark;

import com.lance.game.event.EventBus;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 事件框架压测
 *
 * @author Lance
 * @since 2022/3/28
 */
@BenchmarkMode(Mode.Throughput) // 吞吐量测试
@Fork(1) // 只fork一个子进程
@State(Scope.Thread) // 每个线程都有自己的成员变量
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 输出时间单位
@Warmup(iterations = 3) // 预热3轮
@Measurement(iterations = 5) // 测试5轮
public class EventBenchmarkTest {

    private ApplicationContext ac;
    private EventBus eventBus;

    @Setup(Level.Trial)
    public void init() {
        ac = SpringApplication.run(BenchMarkApp.class);
        eventBus = ac.getBean(EventBus.class);
    }

    @Benchmark
    public void testGameEvent() {
        eventBus.publishEvent(new GameTestEvent(this));
    }

    @Benchmark
    public void testSpringEvent() {
        ac.publishEvent(new SpringTestEvent(this));
    }

    public static void main(String[] args) throws RunnerException {
//        Benchmark                            Mode  Cnt      Score       Error   Units
//        EventBenchmarkTest.testGameEvent    thrpt    5  65488.995 ± 38720.903  ops/ms
//        EventBenchmarkTest.testSpringEvent  thrpt    5  10912.751 ±  1237.262  ops/ms
        Options opt = new OptionsBuilder()
                .include(EventBenchmarkTest.class.getSimpleName())
                .threads(Runtime.getRuntime().availableProcessors())
                .build();
        new Runner(opt).run();
    }
}
