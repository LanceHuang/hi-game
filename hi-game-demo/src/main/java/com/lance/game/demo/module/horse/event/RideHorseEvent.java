package com.lance.game.demo.module.horse.event;

import com.lance.game.demo.module.player.model.Player;
import lombok.Getter;
import lombok.Setter;

/**
 * 上坐骑事件
 *
 * @author Lance
 */
@Getter
@Setter
public class RideHorseEvent {

    /** 上坐骑玩家 */
    private Player player;

    /** 坐骑id */
    private int horseId;
}
