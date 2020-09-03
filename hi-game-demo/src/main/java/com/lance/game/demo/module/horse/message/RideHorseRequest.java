package com.lance.game.demo.module.horse.message;

import lombok.Getter;
import lombok.Setter;

/**
 * 上坐骑请求
 *
 * @author Lance
 */
@Getter
@Setter
public class RideHorseRequest {

    /** 坐骑id */
    private int horseId;
}
