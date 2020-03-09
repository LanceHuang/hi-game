package com.lance.game.module.buff.config;

import lombok.Data;

/**
 * buff配置
 *
 * @author Lance
 */
@Data
public class BuffConfig {

    /** buffId */
    private int    id;
    /** 类型 */
    private int    type;
    /** 持续时间 */
    private long   duration;
    /** 配置值 */
    private String value;

//    private AttributeExpression attributeExpression;
}
