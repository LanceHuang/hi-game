package com.lance.game.demo.module.item.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 道具配置
 *
 * @author Lance
 */
@Getter
@Setter
public class ItemConfig {

    /** 道具id */
    private int id;
    /** 道具名称 */
    private String name;
    /** 道具类型 */
    private int type;

    // todo 属性
}
