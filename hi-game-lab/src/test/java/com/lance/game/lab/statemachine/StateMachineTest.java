package com.lance.game.lab.statemachine;

import com.lance.game.lab.mud.constant.GameObjectStateChangeEvent;
import com.lance.game.lab.mud.constant.GameObjectState;
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
        StateMachineFactory<GameObjectState, GameObjectStateChangeEvent> stateMachineFactory = new StateMachineFactory<>();
        stateMachineFactory.initState(GameObjectState.NONE)
                .trans(GameObjectState.NONE, GameObjectState.PATROL, GameObjectStateChangeEvent.PATROL)
                .trans(GameObjectState.PATROL, GameObjectState.NONE, GameObjectStateChangeEvent.STOP_PATROL)
                .trans(GameObjectState.NONE, GameObjectState.GATHER, GameObjectStateChangeEvent.GATHER)
                .trans(GameObjectState.GATHER, GameObjectState.NONE, GameObjectStateChangeEvent.STOP_GATHER)
                .trans(GameObjectState.GATHER, GameObjectState.BACK_TO_THE_CITY, GameObjectStateChangeEvent.COMPLETE_GATHER)
                .trans(GameObjectState.BACK_TO_THE_CITY, GameObjectState.GATHER, GameObjectStateChangeEvent.CLEAR_GATHER);

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