package com.lance.game.lab.behaviortree.action;

import com.lance.game.lab.behaviortree.BTContext;

/**
 * 巡逻
 *
 * @author Lance
 * @since 2021/6/8
 */
public class Patrol extends ActionNode {

    @Override
    public void doExecute(BTContext context) {
        System.out.println("执行巡逻");
    }
}
