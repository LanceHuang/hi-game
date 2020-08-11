package com.lance.game.demo.module.world.service;

import com.lance.game.demo.constant.I18nId;
import com.lance.game.demo.exception.GameException;
import com.lance.game.demo.log.LoggerUtil;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.world.config.MapConfig;
import com.lance.game.demo.module.world.constant.WorldConstant;
import com.lance.game.demo.module.world.manager.WorldManager;
import com.lance.game.demo.module.world.model.MapModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 */
@Service
public class WorldService implements IWorldService {

    @Resource
    private WorldManager worldManager;

    /** 地图模型组：地图id->地图模型 */
    private Map<Integer, MapModel> mapModelMap;

    @Override
    public void init() {
        initMap();
        initScene();
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        this.mapModelMap = new HashMap<>();
        for (MapConfig mapConfig : worldManager.getAllMapConfig()) {
            this.mapModelMap.put(mapConfig.getMapId(), new MapModel(mapConfig.getMapId()));
        }
    }

    /**
     * 初始化场景
     */
    private void initScene() {
        for (MapModel mapModel : this.mapModelMap.values()) {
            for (int i = 0; i < WorldConstant.INIT_CHANNEL_NUM; i++) {
                mapModel.createScene();
            }
        }
    }

    @Override
    public void enterMap(Player player, int mapId) {
        MapModel mapModel = this.mapModelMap.get(mapId);
        if (mapModel == null) {
            LoggerUtil.error("玩家【{}】进入的地图【{}】不存在", player.getAccount(), mapId);
            throw new GameException(I18nId.WORLD_NOT_EXIST);
        }

        mapModel.enter(player);
    }
}
