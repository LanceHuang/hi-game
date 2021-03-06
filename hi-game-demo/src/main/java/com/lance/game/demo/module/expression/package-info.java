/**
 * 表达式解析：策划一般会用MVEL或者某种方式配置提供公式，然后根据运行时的数据计算结果，在程序中可以以Expression对象为参数传递公式对象，从而获得高阶函数的效果（属性计算时，需要根据玩家等级进行计算）。
 * 同时，因为策划配表习惯与开发不同，可能提供方法转换公式（策划用LV，开发者用XX.getLevel()）
 */
package com.lance.game.demo.module.expression;