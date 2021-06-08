package com.lance.game.lab.behaviortree.control;

import com.lance.game.lab.behaviortree.BTNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制节点
 *
 * @author Lance
 * @since 2021/6/7
 */
public abstract class ControlNode extends BTNode {

    protected List<BTNode> childList = new ArrayList<>();

    public ControlNode addChild(BTNode node) {
        this.childList.add(node);
        return this;
    }

}
