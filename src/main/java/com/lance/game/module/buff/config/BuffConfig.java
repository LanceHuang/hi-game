package com.lance.game.module.buff.config;

import com.lance.game.module.buff.model.BuffType;
import com.lance.game.module.expression.model.AttributeExpression;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/7/2 20:24
 */
@Data
public class BuffConfig {

    private int      id;
    private BuffType type;
    private long     duration;
    private String   value;

    private AttributeExpression attributeExpression;
}
