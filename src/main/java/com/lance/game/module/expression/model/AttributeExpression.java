package com.lance.game.module.expression.model;

import lombok.Data;

/**
 * 属性公式
 *
 * @author Lance
 * @since 2019/7/3 18:04
 */
@Data
public class AttributeExpression extends MVELExpression {

    /** 原始公式 */
    private String pureExpression;

    public static AttributeExpression valueOf(String expression) {
        AttributeExpression attributeExpression = new AttributeExpression();
        attributeExpression.setPureExpression(expression);
        attributeExpression.setExpression(parseExpression(expression));
        return attributeExpression;
    }

    public static String parseExpression(String expression) {
        // todo 策划格式转换成程序使用的格式， LV => getLevel()
        return expression;
    }

}
