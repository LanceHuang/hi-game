package com.lance.game.lab.behaviortree.condition;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BTStatus;

/**
 * 是否可以攻击
 *
 * @author Lance
 * @since 2021/6/8
 */
public class CanAttack extends ConditionNode {

    @Override
    public BTStatus execute(BTContext context) {
        System.out.println("是否可以攻击：true");
        return BTStatus.SUCCESS;
    }
}
