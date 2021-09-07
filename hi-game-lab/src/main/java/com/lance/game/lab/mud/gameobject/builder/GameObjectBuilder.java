package com.lance.game.lab.mud.gameobject.builder;

import com.lance.game.lab.mud.gameobject.GameObject;

/**
 * 游戏对象构造器
 *
 * @author Lance
 * @since 2021/9/7
 */
public abstract class GameObjectBuilder<T extends GameObject> {

    /**
     * 构造游戏对象
     *
     * @param configId 配置id
     * @param id       对象标识
     * @return 游戏对象
     */
    public abstract T build(int configId, long id);
}
