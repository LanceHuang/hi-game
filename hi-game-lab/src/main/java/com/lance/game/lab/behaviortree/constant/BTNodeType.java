package com.lance.game.lab.behaviortree.constant;

import com.lance.game.lab.behaviortree.BTNode;
import com.lance.game.lab.behaviortree.action.Attack;
import com.lance.game.lab.behaviortree.action.Dead;
import com.lance.game.lab.behaviortree.action.Patrol;
import com.lance.game.lab.behaviortree.action.Retreat;
import com.lance.game.lab.behaviortree.condition.CanAttack;
import com.lance.game.lab.behaviortree.condition.IsDead;
import com.lance.game.lab.behaviortree.condition.HasTarget;
import com.lance.game.lab.behaviortree.control.Parallel;
import com.lance.game.lab.behaviortree.control.Selector;
import com.lance.game.lab.behaviortree.control.Sequence;

/**
 * 行为树节点类型
 *
 * @author Lance
 * @since 2021/6/8
 */
public enum BTNodeType {

    /** 选择 */
    SELECTOR(Selector.class),
    /** 序列 */
    SEQUENCE(Sequence.class),
    /** 并行 */
    PARALLEL(Parallel.class),

    /** 巡逻 */
    PATROL(Patrol.class),
    /** 攻击 */
    ATTACK(Attack.class),
    /** 撤退 */
    RETREAT(Retreat.class),
    /** 死亡 */
    DEAD(Dead.class),

    /** 是否有目标 */
    HAS_TARGET(HasTarget.class),
    /** 是否已死亡 */
    IS_DEAD(IsDead.class),
    /** 是否可以攻击 */
    CAN_ATTACK(CanAttack.class),
    ;

    private final Class<? extends BTNode> clazz;

    BTNodeType(Class<? extends BTNode> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends BTNode> getClazz() {
        return clazz;
    }
}
