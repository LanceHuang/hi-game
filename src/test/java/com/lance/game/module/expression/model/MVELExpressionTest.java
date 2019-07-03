package com.lance.game.module.expression.model;

import org.junit.Test;

public class MVELExpressionTest {

    @Test
    public void test() {
        String expression = "false";
//        String expression = "56+82*26";
        MVELExpression mvelExpression = MVELExpression.valueOf(expression);
        System.out.println(mvelExpression.calculateBoolean(mvelExpression));
    }
}