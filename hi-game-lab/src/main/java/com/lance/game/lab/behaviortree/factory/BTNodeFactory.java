package com.lance.game.lab.behaviortree.factory;

import com.lance.game.lab.behaviortree.BTNode;
import com.lance.game.lab.behaviortree.constant.BTNodeType;

/**
 * 行为树节点工厂
 *
 * @author Lance
 * @since 2021/6/8
 */
public class BTNodeFactory {

    /**
     * 创建节点
     *
     * @param type 节点类型
     * @return 节点
     */
    @SuppressWarnings("unchecked")
    public static <T extends BTNode> T createNode(BTNodeType type) {
        try {
            return (T) type.getClazz().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed to create node: " + type);
    }

}
