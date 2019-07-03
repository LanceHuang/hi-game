package com.lance.game.module.buff.model;

import com.lance.game.module.buff.config.BuffConfig;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/7/2 20:25
 */
@Data
public abstract class AbstractBuff {

    private int      id;
    private BuffType type;
    private long     duration;
    private long     endTime;

    public void init(BuffConfig buffConfig) {
        this.id = buffConfig.getId();
        this.type = buffConfig.getType();
        this.duration = buffConfig.getDuration();
        this.endTime = System.currentTimeMillis() + this.duration;
    }

}
