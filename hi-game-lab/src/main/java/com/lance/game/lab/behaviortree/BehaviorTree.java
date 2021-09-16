package com.lance.game.lab.behaviortree;

/**
 * 行为树
 *
 * @author Lance
 * @since 2021/6/7
 */
public class BehaviorTree {

    /** 根节点 */
    protected BTNode root;

    public BehaviorTree(BTNode root) {
        this.root = root;
    }

    /**
     * 执行
     *
     * @param context 上下文
     */
    public void execute(BTContext context) {
        this.root.execute(context);
    }
}
