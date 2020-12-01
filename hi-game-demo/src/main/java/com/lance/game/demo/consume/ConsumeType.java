package com.lance.game.demo.consume;

import com.lance.game.demo.consume.impl.AndConsume;
import com.lance.game.demo.consume.impl.ItemConsume;
import com.lance.game.demo.consume.impl.TrueConsume;

/**
 * 消耗类型
 *
 * @author Lance
 */
public enum ConsumeType {

    /** 永真消耗 */
    TRUE(TrueConsume.class),
    /** 与消耗 */
    AND(AndConsume.class),
    /** 道具消耗 */
    ITEM(ItemConsume.class);

    private final Class<? extends IConsume> clazz;

    ConsumeType(Class<? extends IConsume> clazz) {
        this.clazz = clazz;
    }

    /**
     * 创建消耗
     */
    public IConsume create() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("创建消耗失败：" + this.name());
    }
}
