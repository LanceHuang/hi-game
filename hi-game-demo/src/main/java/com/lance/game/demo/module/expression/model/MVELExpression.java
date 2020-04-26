package com.lance.game.demo.module.expression.model;

import org.mvel2.MVEL;

/**
 * MVEL表达式。因为不知道其他的表达式工具会有怎样的功能，所以这里专门处理MVEL表达式
 *
 * @author Lance
 * @since 2019/7/2 21:57
 */
public class MVELExpression {

    // todo 感觉还是要改一下

    private String expression;

    public static MVELExpression valueOf(String expression) {
        MVELExpression mvelExpression = new MVELExpression();
        mvelExpression.expression = expression;
        return mvelExpression;
    }

    public Object calculate(Object ctx) {
        return MVEL.eval(expression, ctx);
    }

    public Integer calculateInt(Object ctx) {
        return MVEL.eval(expression, ctx, Integer.class);
    }

    public Long calculateLong(Object ctx) {
        return MVEL.eval(expression, ctx, Long.class);
    }

    public Boolean calculateBoolean(Object ctx) {
        return MVEL.eval(expression, ctx, Boolean.class);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
