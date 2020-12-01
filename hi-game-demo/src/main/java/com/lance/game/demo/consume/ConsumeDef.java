package com.lance.game.demo.consume;

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
