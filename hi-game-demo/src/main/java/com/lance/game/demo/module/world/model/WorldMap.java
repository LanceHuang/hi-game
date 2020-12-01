package com.lance.game.demo.module.world.model;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.log.LoggerUtil;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.world.config.MapConfig;
import com.lance.game.demo.module.world.manager.WorldManager;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 地图
 *
 * @author Lance
 */
@Data
public class WorldMap {

    /** 地图id */
    private int mapId;
    /** 初始频道数 */
    private int initChannelNum;
    /** 最大频道数 */
    private int maxChannelNum;
    /** 最大玩家数 */
    private int maxPlayerNum;
    /** 增线玩家数 */
    private int addPlayerNum;

    /** 场景组：channelId->场景 */
    private Map<Integer, Scene> sceneMap;

    /** 下一个频道id */
    private int nextChannelId = 1;

    private Lock lock = new ReentrantLock();

    public WorldMap(MapConfig mapConfig) { // 这里不存储mapConfig，为了后面可以热更
        this.mapId = mapConfig.getMapId();
        this.initChannelNum = mapConfig.getInitChannelNum();
        this.maxChannelNum = mapConfig.getMaxChannelNum();
        this.maxPlayerNum = mapConfig.getMaxPlayerNum();
        this.addPlayerNum = mapConfig.getAddPlayerNum();
        this.sceneMap = new ConcurrentHashMap<>();
    }

    /**
     * 初始化
     */
    public void init() {
        for (int i = 0; i < this.initChannelNum; i++) {
            createScene();
        }
    }

    /**
     * 创建场景
     */
    public Scene createScene() {
        lock.lock();
        try {
            // 没有可创建场景
            if (this.sceneMap.size() >= this.maxChannelNum) {
                return null;
            }

            // 生成频道id
            int channelId = nextChannelId();
            if (channelId <= 0) {
                return null;
            }

            // 生成场景
            Scene scene = new Scene(this.mapId, channelId);
            this.sceneMap.put(channelId, scene);
            return scene;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 删除场景
     *
     * @param channelId 频道id
     */
    public void deleteScene(int channelId) {
        lock.lock();
        try {
            this.sceneMap.remove(channelId);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取下一个频道id
     */
    private int nextChannelId() {
        // 先按顺序 1 2 3 取值（不需要每次都遍历）
        if (!this.sceneMap.containsKey(this.nextChannelId) && this.nextChannelId <= this.maxChannelNum) {
            return this.nextChannelId++;
        }

        // 再for循环遍历频道id
        for (int i = 1; i <= this.maxChannelNum; i++) {
            if (this.sceneMap.containsKey(i)) {
                continue;
            }
            this.nextChannelId = i + 1; // 很有可能一个连续的范围都没有初始化
            return i;
        }
        return -1;
    }

    /**
     * 进图校验
     */
    public void verify(Player player) {
        MapConfig mapConfig = WorldManager.getInstance().getMapConfig(this.mapId);
        mapConfig.getCondition().verifyThrow(player);
        mapConfig.getConsume().verifyThrow(player);
    }

    /**
     * 进入地图
     */
    public void enter(Player player) {
        // 扣除进图材料
        MapConfig mapConfig = WorldManager.getInstance().getMapConfig(this.mapId);
        mapConfig.getConsume().consume(player);

        Scene scene = selectOrCreateScene();
        if (scene == null) {
            LoggerUtil.error("玩家【{}】进入的地图【{}】已满员", player.getAccount(), mapId);
            throw new GameException(I18nId.WORLD_FULL_SCENE);
        }

        scene.addPlayer(player);
        LoggerUtil.info("玩家【{}】进入地图【{}】分线【{}】，当前场景人数【{}】",
                player.getAccount(), scene.getMapId(), scene.getChannelId(), scene.countPlayer());
    }

    /**
     * 选择或创建场景
     */
    private Scene selectOrCreateScene() { // todo 刚select出来的场景，有可能被删除了
        // 先选择现有的场景
        for (Scene scene : this.sceneMap.values()) {
            if (scene.countPlayer() >= this.addPlayerNum) {
                continue;
            }
            return scene;
        }

        // 再尝试创建场景
        if (this.sceneMap.size() < this.maxChannelNum) {
            return createScene();
        }

        // 最后再挑选最少人的场景
        int minPlayerNum = Integer.MAX_VALUE;
        Scene selectedScene = null;
        for (Scene scene : this.sceneMap.values()) {
            if (scene.countPlayer() < minPlayerNum) {
                minPlayerNum = scene.countPlayer();
                selectedScene = scene;
            }
        }

        // 满员则不允许进入
        if (selectedScene != null && selectedScene.countPlayer() < this.maxPlayerNum) {
            return selectedScene;
        }
        return null;
    }

    /**
     * 离开地图
     */
    public void leave(Player player) {
        WorldPosition worldPosition = player.getWorldPosition();
        int orgMapId = worldPosition.getMapId(); // 原地图id
        int orgChannelId = worldPosition.getChannelId(); // 原频道id


        Scene orgScene = this.sceneMap.get(orgChannelId);
        // todo removePlayer(player)

//        LoggerUtil.info("玩家【{}】离开地图【{}】分线【{}】，当前场景人数【{}】",
//                player.getAccount(), scene.getMapId(), scene.getChannelId(), scene.getPlayerCount());
    }
}
