package com.lance.game.demo.module.world.model;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.log.LoggerUtil;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.world.constant.WorldConstant;
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

    /** 场景组：channelId->场景 */
    private Map<Integer, Scene> sceneMap;

    public MapModel(int mapId) {
        this.mapId = mapId;
        this.sceneMap = new ConcurrentHashMap<>();
    }

    /**
     * 创建场景
     */
    public Scene createScene() {
        int channelId = nextChannelId();
        if (channelId <= 0) {
            return null;
        }

        Scene scene = new Scene(this.mapId, channelId);
        this.sceneMap.put(channelId, scene);
        return scene;
    }

    /**
     * 获取下一个频道id
     */
    private int nextChannelId() {
        for (int i = 1; i <= WorldConstant.MAX_CHANNEL_NUM; i++) {
            if (this.sceneMap.containsKey(i)) {
                continue;
            }
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
        System.out.println(String.format("玩家【%s】进入地图【%d】，分线【%d】，当前场景人数【%d】",
                player.getAccount(), scene.getMapId(), scene.getChannelId(), scene.getPlayerCount()));
    }

    /**
     * 选择或创建场景
     */
    private Scene selectOrCreateScene() {
        // todo 这个可以搞成策略模式，尽量让场景数慢慢减少
        for (Scene scene : this.sceneMap.values()) {
            if (scene.getPlayerCount() >= WorldConstant.MAX_CHANNEL_PLAYER_NUM) {
                continue;
            }
            return scene;
        }
        return createScene();
    }
}
