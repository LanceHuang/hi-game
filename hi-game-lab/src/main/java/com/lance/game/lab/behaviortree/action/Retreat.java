package com.lance.game.lab.behaviortree.action;

import com.lance.game.lab.behaviortree.BTContext;

/**
 * 撤退
 *
 * @author Lance
 * @since 2021/6/8
 */
public class Retreat extends ActionNode {

    @Override
    public void doExecute(BTContext context) {
        System.out.println("执行撤退");
    }
}
