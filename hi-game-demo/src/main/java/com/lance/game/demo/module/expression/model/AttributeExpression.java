package com.lance.game.demo.module.expression.model;

/**
 * 属性公式
 *
 * @author Lance
 * @since 2019/7/3 18:04
 */
public class AttributeExpression extends MVELExpression {

    /** 原始公式 */
    private String pureExpression;

    public static AttributeExpression valueOf(String expression) {
        AttributeExpression attributeExpression = new AttributeExpression();
        attributeExpression.setPureExpression(expression);
        attributeExpression.setExpression(parseExpression(expression));
        return attributeExpression;
    }

    /**
     * LV*8+BASE_HP => getLevel()*8+getBaseHp()
     */
    public static String parseExpression(String expression) {
        // todo
        return expression;
    }

    public String getPureExpression() {
        return pureExpression;
    }

    public void setPureExpression(String pureExpression) {
        this.pureExpression = pureExpression;
    }
}
