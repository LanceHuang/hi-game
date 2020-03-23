package com.lance.game.module.buff.model;

import com.lance.game.module.player.model.Player;
import com.lance.log.LogCode;
import com.lance.log.LogModule;
import com.lance.log.LoggerUtil;

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

    private Player owner;

    public void add(AbstractBuff buff) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + buff.getDuration();
        buff.setStartTime(startTime);
        buff.setEndTime(endTime);

        // todo 创建定时器

        this.buffMap.put(buff.getId(), buff);

        LoggerUtil.log(LogModule.BUFF, LogCode.BUFF_ADD, "account:{},id:{},startTime:{},duration:{},endTime:{} 添加buff",
                this.owner.getAccount(), buff.getId(), buff.getStartTime(), buff.getDuration(), buff.getEndTime());
    }

    public void remove(int id) {
        AbstractBuff buff = this.buffMap.remove(id);
        if (buff == null) {
            return;
        }

//        buff.getDeactivateFuture().cancel(true); // todo 关闭定时器

        LoggerUtil.log(LogModule.BUFF, LogCode.BUFF_REMOVE, "account:{},id:{},startTime:{},duration:{},endTime:{} 移除buff",
                this.owner.getAccount(), buff.getId(), buff.getStartTime(), buff.getDuration(), buff.getEndTime());
    }

    public boolean contains(int id) {
        return this.buffMap.containsKey(id);
    }

    //============================= Getter/Setter ============================

    public Map<Integer, AbstractBuff> getBuffMap() {
        return buffMap;
    }

    public void setBuffMap(Map<Integer, AbstractBuff> buffMap) {
        this.buffMap = buffMap;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
