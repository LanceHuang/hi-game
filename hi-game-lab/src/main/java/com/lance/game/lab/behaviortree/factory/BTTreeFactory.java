package com.lance.game.lab.behaviortree.factory;

import com.lance.game.lab.behaviortree.BTNode;
import com.lance.game.lab.behaviortree.BehaviorTree;
import com.lance.game.lab.behaviortree.constant.BTNodeType;
import com.lance.game.lab.behaviortree.control.ControlNode;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 行为树工厂
 *
 * @author Lance
 * @since 2021/6/8
 */
public class BTTreeFactory {

    /**
     * 创建行为树
     *
     * @return 行为树
     */
    @SuppressWarnings("unchecked")
    public static BehaviorTree createTree(String btFile) {
        List<BTNodeDefinition> definitions = parseDefinitions(btFile);
        BTNode root = createNode(definitions, 0);
        return new BehaviorTree(root);
    }

    /**
     * 解析定义
     *
     * @param btFile 定义文件
     * @return 定义
     */
    private static List<BTNodeDefinition> parseDefinitions(String btFile) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(BTTreeFactory.class.getClassLoader().getResourceAsStream(btFile))
            );
            String line;
            List<BTNodeDefinition> definitions = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                // 去掉注释
                int commentIndex = line.indexOf("#");
                if (commentIndex >= 0) {
                    line = line.substring(0, commentIndex);
                }

                // 计算层级
                int level = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (!Character.isSpaceChar(line.charAt(i))) {
                        level = i;
                        break;
                    }
                }
                // 一个tab等于4个空格
                level /= 4;

                // 解析类型
                String typeName = line.substring(level).trim();
                if (typeName.isEmpty()) {
                    continue;
                }
                BTNodeType type = BTNodeType.valueOf(typeName);


                // 生成定义
                BTNodeDefinition definition = new BTNodeDefinition();
                definition.setLevel(level);
                definition.setType(type);
                definitions.add(definition);
            }
            bufferedReader.close();
            return definitions;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建节点
     *
     * @param definitions 定义
     * @param index       当前下标
     * @return 节点
     */
    private static BTNode createNode(List<BTNodeDefinition> definitions, int index) {
        BTNodeDefinition definition = definitions.get(index);
        int currLevel = definition.getLevel();
        BTNode node = BTNodeFactory.createNode(definition.getType());

        BTNodeDefinition nextDefinition = definitions.get(index + 1);
        if (currLevel >= nextDefinition.getLevel()) {
            return node;
        } else {
            if (node instanceof ControlNode) {
                ControlNode controlNode = (ControlNode) node;
                int tempLevel = nextDefinition.getLevel();
                for (int i = index + 1; i < definitions.size(); i++) {
                    BTNodeDefinition nextDefinitionT = definitions.get(i);
                    if (nextDefinitionT.getLevel() == tempLevel) {
                        BTNode child = createNode(definitions, i);
                        controlNode.addChild(child);
                    } else {
                        break;
                    }
                }
            } else {
                // todo 异常

                return null;
            }
        }
        return node;
    }

    @Getter
    @Setter
    private static class BTNodeDefinition {

        private int level;
        private BTNodeType type;
    }
}
