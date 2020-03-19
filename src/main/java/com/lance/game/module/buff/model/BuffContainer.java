package com.lance.game.module.buff.model;

import com.lance.game.module.player.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * buff容器
 *
 * @author Lance
 */
public class BuffContainer {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

        logger.info("Add buff account:{},id:{},startTime:{},duration:{},endTime:{}",
                this.owner.getAccount(), buff.getId(), buff.getStartTime(), buff.getDuration(), buff.getEndTime());
    }

    public void remove(int id, String desc) {
        AbstractBuff buff = this.buffMap.remove(id);
        if (buff == null) {
            return;
        }

//        buff.getDeactivateFuture().cancel(true); // todo 关闭定时器

        logger.info("Remove buff account:{},id:{},startTime:{},duration:{},endTime:{},desc:{}",
                this.owner.getAccount(), buff.getId(), buff.getStartTime(), buff.getDuration(), buff.getEndTime(), desc);
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
