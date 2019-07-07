package com.lance.game.module.item.model;

/**
 * @author Lance
 * @since 2019/7/4 20:36
 */
public enum ItemType {

    // todo 暂时没想到 武器和装备 的区别
//    /** 武器 */
//    WEAPON,

    /** 装备 */
    EQUIPMENT() {
        @Override
        public AbstractItem create() {
            return new Equipment();
        }
    },
    /** 药物 */
    MEDICINE() {
        @Override
        public AbstractItem create() {
            return new Medicine();
        }
    },
    ;

    // todo 因为考虑到可能需要自定义道具的信息，所以这里留出create方法
    // 1. 加数字标识？用于存数据库

    /**
     * 创建道具
     */
    public abstract AbstractItem create();

}
