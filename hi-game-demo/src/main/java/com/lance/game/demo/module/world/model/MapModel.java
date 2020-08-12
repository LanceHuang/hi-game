package com.lance.game.demo.module.world.model;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.log.LoggerUtil;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.world.config.MapConfig;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 地图模型
 *
 * @author Lance
 */
@Data
public class MapModel {

    /** 地图id */
    private int mapId;
    /** 初始频道数 */
    private int initChannelNum;
    /** 最大频道数 */
    private int maxChannelNum;
    /** 最大玩家数 */
    private int maxPlayerNum;

    /** 场景组：channelId->场景 */
    private Map<Integer, Scene> sceneMap;

    /** 下一个频道id */
    private int nextChannelId = 1;

    public MapModel(MapConfig mapConfig) { // 这里不存储mapConfig，为了后面可以热更
        this.mapId = mapConfig.getMapId();
        this.initChannelNum = mapConfig.getInitChannelNum();
        this.maxChannelNum = mapConfig.getMaxChannelNum();
        this.maxPlayerNum = mapConfig.getMaxPlayerNum();
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
    public Scene createScene() { // todo 创建和回收场景需要上锁
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
     * 进入地图
     */
    public void enter(Player player) {
        Scene scene = selectOrCreateScene();
        if (scene == null) {
            LoggerUtil.error("玩家【{}】进入的地图【{}】已满员", player.getAccount(), mapId);
            throw new GameException(I18nId.WORLD_FULL_SCENE);
        }

        scene.addPlayer();
        // todo
        System.out.println(String.format("玩家【%s】进入地图【%d】分线【%d】，当前场景人数【%d】",
                player.getAccount(), scene.getMapId(), scene.getChannelId(), scene.getPlayerCount()));
    }

    /**
     * 选择或创建场景
     */
    private Scene selectOrCreateScene() {
        // todo 这个可以搞成策略模式，尽量让场景数慢慢减少
        for (Scene scene : this.sceneMap.values()) {
            if (scene.getPlayerCount() >= this.maxPlayerNum) {
                continue;
            }
            return scene;
        }
        return createScene();
    }
}
