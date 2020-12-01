package com.lance.game.demo.consume;

import lombok.Getter;
import lombok.Setter;

/**
 * 消耗定义
 *
 * @author Lance
 */
@Getter
@Setter
public class ConsumeDef {

    /** 条件类型 */
    private ConsumeType type;

    /** 条件值 */
    private String value;
}
