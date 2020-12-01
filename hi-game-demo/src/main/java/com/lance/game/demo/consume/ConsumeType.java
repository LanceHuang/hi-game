package com.lance.game.demo.consume;

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
    ITEM(ItemConsume.class),
    /** 钻石消耗 */
    GOLD(GoldConsume.class);

    private Class<? extends IConsume> clazz;

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

    //================================= Getter/Setter ==================================

    public Class<? extends IConsume> getClazz() {
        return clazz;
    }

}
