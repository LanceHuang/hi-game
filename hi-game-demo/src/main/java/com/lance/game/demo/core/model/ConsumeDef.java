package com.lance.game.demo.core.model;

import com.lance.game.demo.core.constant.ConsumeType;
import lombok.Data;

/**
 * 消耗定义
 *
 * @author Lance
 */
@Data
public class ConsumeDef {

    /** 条件类型 */
    private ConsumeType type;

    /** 条件值 */
    private String value;
}
