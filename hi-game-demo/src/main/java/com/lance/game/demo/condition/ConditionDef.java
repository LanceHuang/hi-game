package com.lance.game.demo.condition;

import lombok.Data;

/**
 * 条件定义
 *
 * @author Lance
 */
@Data
public class ConditionDef {

    /** 条件类型 */
    private ConditionType type;

    /** 条件值 */
    private String value;
}
