package com.lance.game.module.buff.model;

import java.util.HashMap;
import java.util.Map;

/**
 * buff容器
 *
 * @author Lance
 */
public class BuffContainer {

    // todo
    // 1. Map
    // 2. 定时器控制buff的失效
    // 3. 互斥？按组别划分
    // 4. 叠加？什么场景？

    private Map<Integer, AbstractBuff> buffMap = new HashMap<>();

    public static BuffContainer valueOf() {
        return new BuffContainer();
    }

    public void add(AbstractBuff buff) {
        if (buff == null) {
            return;
        }

        long now = System.currentTimeMillis();
        if (buff.getEndTime() <= now) {
//            LoggerUtil.error("buff已过期");
            return;
        }

//        创建定时器
        this.buffMap.put(buff.getId(), buff);

        // todo 日志，还需要记录玩家信息和buff信息
    }

    public void remove(int id) {
        AbstractBuff buff = this.buffMap.remove(id);
        if (buff == null) {
            return;
        }

        buff.getDeactivateFuture().cancel(true); // todo 关闭定时器
        // todo 日志，还需要记录玩家信息和buff信息
    }

    public boolean contains(int id) {
        return this.buffMap.containsKey(id);
    }
}
