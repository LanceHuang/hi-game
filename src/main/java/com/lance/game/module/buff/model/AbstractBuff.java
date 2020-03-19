package com.lance.game.module.buff.model;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.player.model.Player;

import java.util.concurrent.Future;

/**
 * 德鲁伊给蛋哥施放了“生命复苏”buff。这里德鲁伊是施法者，蛋哥是持有者。
 * <ol>
 * <li>添加400战斗力，持续4秒</li>
 * <li>添加40%战斗力，持续4秒</li>
 * </ol>
 *
 * @author Lance
 */
public abstract class AbstractBuff {

    protected int  id;
    protected int  type;
    protected long startTime;
    protected long duration;
    protected long endTime;

    protected BuffConfig buffConfig;

    //    // todo 其实有点想将这个抽出来，单独用于管理持续时间相关的内容

    /** 施法者 */
    protected Player caster; // todo 没必要直接用player实体
    /** 持有者 */
    protected Player owner;

    protected Future<?> deactivateFuture;

    protected AbstractBuff(BuffConfig buffConfig) {
        this.id = buffConfig.getId();
        this.type = buffConfig.getType();
//        this.startTime = System.currentTimeMillis();
        this.duration = buffConfig.getDuration();
//        this.endTime = this.startTime + this.duration;
        this.buffConfig = buffConfig;
    }

    // todo 叠加次数


    // todo 定时器？

    /**
     * 激活
     */
    public abstract void activate();

    // todo 直接cancel？

    /**
     * 失效
     */
    public abstract void deactivate();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Future<?> getDeactivateFuture() {
        return deactivateFuture;
    }

    public void setDeactivateFuture(Future<?> deactivateFuture) {
        this.deactivateFuture = deactivateFuture;
    }
}
