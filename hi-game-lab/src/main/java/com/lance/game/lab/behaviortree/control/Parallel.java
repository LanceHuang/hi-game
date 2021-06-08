package com.lance.game.lab.behaviortree.control;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BTNode;
import com.lance.game.lab.behaviortree.BTStatus;

/**
 * 并行节点
 *
 * @author Lance
 * @since 2021/6/7
 */
public class Parallel extends ControlNode {

    @Override
    public BTStatus execute(BTContext context) {
        for (BTNode child : childList) {
            child.execute(context);
        }
        return BTStatus.SUCCESS;
    }
}
