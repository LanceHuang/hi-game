package com.lance.game.lab.mud.gameobject.impl;

/**
 * 工厂建筑
 *
 * @author Lance
 * @since 2021/9/7
 */
public class FactoryBuilding extends Building {

    // todo 状态 制造中

    private int spawnX;

    private int spawnY;

    public FactoryBuilding(long id) {
        super(id);
    }

    /**
     * 制造游戏单位
     *
     * @param objectConfigId 游戏配置id
     */
    public void makeGameObject(int objectConfigId) {
        // todo
    }
}
