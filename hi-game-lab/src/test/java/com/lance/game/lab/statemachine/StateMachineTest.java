package com.lance.game.lab.statemachine;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;
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

    @Test
    public void test() throws IOException {
        StateMachineFactory<ArmyState, ArmyStateChangeEvent> stateMachineFactory = new StateMachineFactory<>();
        stateMachineFactory.initState(ArmyState.NONE)
                .trans(ArmyState.NONE, ArmyState.PATROL, ArmyStateChangeEvent.PATROL)
                .trans(ArmyState.PATROL, ArmyState.NONE, ArmyStateChangeEvent.STOP_PATROL)
                .trans(ArmyState.NONE, ArmyState.GATHER, ArmyStateChangeEvent.GATHER)
                .trans(ArmyState.GATHER, ArmyState.NONE, ArmyStateChangeEvent.STOP_GATHER)
                .trans(ArmyState.GATHER, ArmyState.BACK_TO_THE_CITY, ArmyStateChangeEvent.COMPLETE_GATHER)
                .trans(ArmyState.BACK_TO_THE_CITY, ArmyState.GATHER, ArmyStateChangeEvent.CLEAR_GATHER);

        ArmyCreator armyCreator = new ArmyCreator(stateMachineFactory);
        String name = "Lance";
        ArmyObject armyObject = armyCreator.createArmyObject(name);
        System.out.println("创建军队：" + name);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // tick
        scheduledExecutorService.scheduleWithFixedDelay(armyObject::tick, randomValue(), TICK_DELAY, TimeUnit.MILLISECONDS);

        // 模拟操作
        Scanner scanner = new Scanner(System.in);
        String ins = scanner.nextLine();
        while (!"exit".equalsIgnoreCase(ins)) {
            if ("patrol".equalsIgnoreCase(ins)) {
                armyObject.patrol();
            } else if ("stopPatrol".equalsIgnoreCase(ins)) {
                armyObject.stopPatrol();
            } else if ("gather".equalsIgnoreCase(ins)) {
                armyObject.gather();
            } else if ("stopGather".equalsIgnoreCase(ins)) {
                armyObject.stopGather();
            }
            ins = scanner.nextLine();
        }

        System.in.read();
    }

    private long randomValue() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(MAX_INITIAL_DELAY);
    }
}