package com.lance.game.module.buff.model;

import com.lance.game.module.buff.config.BuffConfig;
import com.lance.game.module.player.model.Player;
import lombok.Data;

/**
 * 德鲁伊给蛋哥施放了“生命复苏”buff。这里德鲁伊是施法者，蛋哥是持有者
 *
 * @author Lance
 * @since 2019/7/2 20:25
 */
@Data
public abstract class AbstractBuff {

    private int      id;
    private BuffType type;
    private long     duration;
    private long     endTime;

    // todo 其实有点想将这个抽出来，单独用于管理持续时间相关的内容
    /** 施法者 */
    private Player caster;
    /** 持有者 */
    private Player owner;

    public void init(BuffConfig buffConfig) {
        this.id = buffConfig.getId();
        this.type = buffConfig.getType();
        this.duration = buffConfig.getDuration();
        this.endTime = System.currentTimeMillis() + this.duration;
    }

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
}
