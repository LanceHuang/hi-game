package com.lance.game.lab.behaviortree;

/**
 * 行为树节点
 *
 * @author Lance
 * @since 2021/6/7
 */
public abstract class BTNode {

    /**
     * 执行节点逻辑
     *
     * @param context 上下文
     * @return 状态
     */
    public abstract BTStatus execute(BTContext context);
}
