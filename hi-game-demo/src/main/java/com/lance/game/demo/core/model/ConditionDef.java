package com.lance.game.demo.core.model;

import com.lance.game.demo.core.condition.ConditionType;
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
