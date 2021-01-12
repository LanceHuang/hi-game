package com.lance.game.demo.core.condition;

import lombok.Getter;
import lombok.Setter;

/**
 * 条件定义
 *
 * @author Lance
 */
@Getter
@Setter
public class ConditionDef {

    /** 条件类型 */
    private ConditionType type;

    /** 条件值 */
    private String value;
}
