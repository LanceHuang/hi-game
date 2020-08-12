package com.lance.game.demo.module.world.manager;

import com.lance.game.demo.module.world.config.MapConfig;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 */
@Repository
public class WorldManager {

    private Map<Integer, MapConfig> mapConfigStorage;

    private static WorldManager instance;

    @PostConstruct
    public void init() {
        instance = this;

        // todo 测试数据
        this.mapConfigStorage = new HashMap<>();
        int[] mapIds = {10083, 10084, 10085, 10086, 10087};
        for (int mapId : mapIds) {
            MapConfig mapConfig = new MapConfig();
            mapConfig.setMapId(mapId);
            mapConfig.setInitChannelNum(1);
            mapConfig.setMaxChannelNum(3);
            mapConfig.setMaxPlayerNum(10);
            mapConfig.setAddPlayerNum(5);
            this.mapConfigStorage.put(mapId, mapConfig);
        }
    }

    public static WorldManager getInstance() {
        return instance;
    }

    /**
     * 获取所有地图配置
     */
    public Collection<MapConfig> getAllMapConfig() {
        return this.mapConfigStorage.values();
    }

    /**
     * 获取地图配置
     */
    public MapConfig getMapConfig(int mapId) {
        return this.mapConfigStorage.get(mapId);
    }
}
