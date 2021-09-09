package com.lance.game.mud.gameobjectbuilder;

import com.lance.game.mud.gameobject.GameObject;

/**
 * 游戏单位建造器
 *
 * @author Lance
 * @since 2021/9/7
 */
public abstract class GameObjectBuilder<T extends GameObject> {

    /**
     * 构造游戏单位
     *
     * @param configId 配置id
     * @param id       单位id
     * @return 游戏单位
     */
    public abstract T build(int configId, long id);
}
