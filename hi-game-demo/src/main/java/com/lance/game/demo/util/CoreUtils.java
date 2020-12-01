package com.lance.game.demo.util;

import com.lance.game.demo.consume.ConsumeType;
import com.lance.game.demo.consume.AbstractConsume;
import com.lance.game.demo.consume.AndConsume;
import com.lance.game.demo.consume.IConsume;
import com.lance.game.demo.consume.ConsumeDef;

/**
 * @author Lance
 */
public class CoreUtils {

    private static final IConsume trueConsume = ConsumeType.TRUE.create();


    /**
     * 解析消耗定义
     */
    public static IConsume parseConsume(ConsumeDef def) {
        AbstractConsume newConsume = (AbstractConsume) def.getType().create();
        newConsume.parse(def.getValue());
        return newConsume;
    }

    /**
     * 解析消耗定义
     */
    public static IConsume parseConsume(ConsumeDef[] defs) {
        if (defs == null || defs.length == 0) {
            return trueConsume;
        }
        if (defs.length == 1) { // 减少小对象
            return parseConsume(defs[0]);
        }

        AndConsume andConsume = (AndConsume) ConsumeType.AND.create();
        for (ConsumeDef def : defs) {
            if (def == null) {
                continue;
            }

            andConsume.addConsume((AbstractConsume) parseConsume(def));
        }
        return andConsume;
    }
}
