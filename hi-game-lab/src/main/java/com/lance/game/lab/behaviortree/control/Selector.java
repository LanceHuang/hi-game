package com.lance.game.lab.behaviortree.control;

import com.lance.game.lab.behaviortree.BTContext;
import com.lance.game.lab.behaviortree.BTNode;
import com.lance.game.lab.behaviortree.BTStatus;

/**
 * 选择节点
 *
 * @author Lance
 * @since 2021/6/7
 */
public class Selector extends ControlNode {

    @Override
    public BTStatus execute(BTContext context) {
        for (BTNode child : childList) {
            BTStatus status = child.execute(context);
            if (status != BTStatus.FAILURE) {
                return status;
            }
        }
        return BTStatus.SUCCESS;
    }
}
