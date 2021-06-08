package com.lance.game.lab.behaviortree;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 行为树上下文
 *
 * @author Lance
 * @since 2021/6/7
 */
@Getter
@Setter
public class BTContext {

    private Map<String, Object> data;
}
