package com.lance.game.lab.statemachine;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2021/9/6
 */
public class StateMachineTest {

    private static final long MAX_INITIAL_DELAY = 1000L;
    private static final long TICK_DELAY = 1000L;
    private static final long MANUAL_DELAY = 5000L;

    @Test
    public void test() throws IOException {
        StateMachineFactory<ArmyState, ArmyStateChangeEvent> stateMachineFactory = new StateMachineFactory<>();
        stateMachineFactory.initState(ArmyState.NONE)
                .trans(ArmyState.NONE, ArmyState.PATROL, ArmyStateChangeEvent.MOVE)
                .trans(ArmyState.PATROL, ArmyState.NONE, ArmyStateChangeEvent.STOP_MOVE);

        ArmyCreator armyCreator = new ArmyCreator(stateMachineFactory);
        String name = "Lance";
        ArmyObject armyObject = armyCreator.createArmyObject(name);
        System.out.println("Create army: " + name);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // tick
        scheduledExecutorService.scheduleWithFixedDelay(armyObject::tick, randomValue(), TICK_DELAY, TimeUnit.MILLISECONDS);
        // 模拟接管
        scheduledExecutorService.scheduleWithFixedDelay(armyObject::stopMove, randomValue(), MANUAL_DELAY, TimeUnit.MILLISECONDS);

        System.in.read();
    }

    private long randomValue() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(MAX_INITIAL_DELAY);
    }
}