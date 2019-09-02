package com.lance.game.module.buff.model;

/**
 * @author Lance
 * @since 2019/7/4 17:56
 */
public class BuffContainer {

    // todo
    // 1. Map
    // 2. 定时器控制buff的失效
    // 3. 互斥？按组别划分
    // 4. 叠加？什么场景？

    public static BuffContainer valueOf() {
        return new BuffContainer();
    }
}
