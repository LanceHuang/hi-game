package com.lance.game.lab.behaviortree.action;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BTNode;
import com.lance.game.lab.behaviortree.BTStatus;

/**
 * 行为节点
 *
 * @author Lance
 * @since 2021/6/7
 */
public abstract class ActionNode extends BTNode {

    @Override
    public BTStatus execute(BTContext context) {
        doExecute(context);
        return BTStatus.EXECUTING;
    }

    public abstract void doExecute(BTContext context);
}
