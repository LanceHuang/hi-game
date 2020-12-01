package com.lance.game.demo.consume;

import com.lance.game.demo.consume.impl.AndConsume;

/**
 * 消耗工具
 *
 * @author Lance
 * @since 2020/12/1
 */
public class ConsumeUtils {

    /** 永真消耗 */
    private static final IConsume trueConsume = ConsumeType.TRUE.create();

    /**
     * 解析消耗定义
     */
    public static IConsume parseConsume(ConsumeDef def) {
        if (def == null) {
            return null;
        }

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

        // 减少小对象
        if (defs.length == 1) {
            IConsume consume = parseConsume(defs[0]);
            return consume == null ? trueConsume : consume;
        }

        // 与消耗
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
