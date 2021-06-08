package com.lance.game.lab.behaviortree;

import com.lance.game.lab.behaviortree.action.Attack;
import com.lance.game.lab.behaviortree.action.Dead;
import com.lance.game.lab.behaviortree.action.Patrol;
import com.lance.game.lab.behaviortree.action.Retreat;

import static com.lance.game.lab.behaviortree.constant.BTNodeType.*;

import com.lance.game.lab.behaviortree.condition.CanAttack;
import com.lance.game.lab.behaviortree.condition.HasTarget;
import com.lance.game.lab.behaviortree.condition.IsDead;
import com.lance.game.lab.behaviortree.control.Selector;
import com.lance.game.lab.behaviortree.control.Sequence;
import com.lance.game.lab.behaviortree.factory.BTNodeFactory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2021/6/7
 */
public class BehaviorTreeTest {

    @Test
    public void test() throws Exception {
        // 目标：
        // 1. 既可以做成组件，用文件配置
        // 2. 又可以在代码中手动组装

        // 攻击
        CanAttack canAttack = BTNodeFactory.createNode(CAN_ATTACK);
        Attack attack = BTNodeFactory.createNode(ATTACK);
        Sequence canAttackSequence = BTNodeFactory.createNode(SEQUENCE);
        canAttackSequence.addChild(canAttack);
        canAttackSequence.addChild(attack);

        // 撤退
        Retreat retreat = BTNodeFactory.createNode(RETREAT);
        Sequence retreatSequence = BTNodeFactory.createNode(SEQUENCE);
        retreatSequence.addChild(retreat);

        // 有目标
        Sequence hasTargetSequence = BTNodeFactory.createNode(SEQUENCE);
        HasTarget hasTarget = BTNodeFactory.createNode(HAS_TARGET);
        hasTargetSequence.addChild(hasTarget);
        hasTargetSequence.addChild(canAttackSequence);
        hasTargetSequence.addChild(retreat);

        // 死亡
        IsDead isDead = BTNodeFactory.createNode(IS_DEAD);
        Dead dead = BTNodeFactory.createNode(DEAD);
        Sequence deadSequence = BTNodeFactory.createNode(SEQUENCE);
        deadSequence.addChild(isDead);
        deadSequence.addChild(dead);

        // 巡逻
        Patrol patrol = BTNodeFactory.createNode(PATROL);
        Sequence patrolSequence = BTNodeFactory.createNode(SEQUENCE);
        patrolSequence.addChild(patrol);

        // 无目标目标
        Sequence noTargetSequence = BTNodeFactory.createNode(SEQUENCE);
        noTargetSequence.addChild(deadSequence);
        noTargetSequence.addChild(patrolSequence);

        // 根结点
        Selector selector = BTNodeFactory.createNode(SELECTOR);
        selector.addChild(hasTargetSequence);
        selector.addChild(noTargetSequence);

        BehaviorTree tree = new BehaviorTree(selector);
        BTContext context = new BTContext();

        // 1秒触发一次
        long delay = 1000L;
        long period = 1000L;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            // log
            System.out.println("==================== tick ====================");

            // execute
            tree.execute(context);
        }, delay, period, TimeUnit.MILLISECONDS);

        // wait
        Thread.sleep(10000L);
    }


}