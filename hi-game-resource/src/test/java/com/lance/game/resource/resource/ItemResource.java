package com.lance.game.resource.resource;

import com.lance.game.resource.annotation.GameResource;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lance
 * @since 2020/12/2
 */
@Getter
@Setter
@GameResource
public class ItemResource {

    /** 道具id */
    private int id;
    /** 道具名称 */
    private String name;
}
