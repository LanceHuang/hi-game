package com.lance.game.lab.mud.battle;

import com.lance.game.lab.mud.battle.action.GameActionType;
import com.lance.game.lab.mud.gameobject.GameObject;

import java.util.Map;

/**
 * 战役
 *
 * @author Lance
 * @since 2021/9/7
 */
public class BattleContext {

    private final long id;

    private final long createTime;

    private int maxX;

    private int maxY;

    private Map<Long, GameObject> gameObjectMap;

    public BattleContext(long id) {
        this.id = id;
        this.createTime = System.currentTimeMillis();
    }

    /**
     * 初始化战役
     */
    public void init() {

    }

    /**
     * 添加游戏对象
     *
     * @param gameObject 游戏对象
     * @param x          x
     * @param y          y
     */
    public void addGameObject(GameObject gameObject, int x, int y) {
        checkGameObject(gameObject, x, y);
        spawnGameObject(gameObject, x, y);
    }

    /**
     * 校验游戏对象
     *
     * @param gameObject 游戏对象
     * @param x          x
     * @param y          y
     */
    private void checkGameObject(GameObject gameObject, int x, int y) {
        // todo
    }

    /**
     * 放置游戏对象
     *
     * @param gameObject 游戏对象
     * @param x          x
     * @param y          y
     */
    private void spawnGameObject(GameObject gameObject, int x, int y) {
        // todo
    }

    /**
     * 执行指令
     *
     * @param id             对象标识
     * @param gameActionType 行为类型
     */
    public void execute(long id, GameActionType gameActionType, Map<String, String> params) {
        GameObject gameObject = gameObjectMap.get(id);
        if (gameObject == null) {
            return;
        }

        gameActionType.execute(this, gameObject, params);
    }
}
