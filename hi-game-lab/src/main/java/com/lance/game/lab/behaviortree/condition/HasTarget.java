package com.lance.game.lab.behaviortree.condition;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BTStatus;

/**
 * 判断是否有目标
 *
 * @author Lance
 * @since 2021/6/8
 */
public class HasTarget extends ConditionNode {

    @Override
    public BTStatus execute(BTContext context) {
        System.out.println("判断是否有目标：true");
        return BTStatus.SUCCESS;
    }
}
