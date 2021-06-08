package com.lance.game.lab.behaviortree.condition;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BTStatus;

/**
 * 是否已死亡
 *
 * @author Lance
 * @since 2021/6/8
 */
public class IsDead extends ConditionNode {

    @Override
    public BTStatus execute(BTContext context) {
        System.out.println("是否已死亡：true");
        return BTStatus.SUCCESS;
    }
}
