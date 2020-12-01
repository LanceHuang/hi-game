package com.lance.game.demo.module.world.service;

import com.lance.game.demo.core.constant.I18nId;
import com.lance.game.demo.core.exception.GameException;
import com.lance.game.demo.core.log.LoggerUtil;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.module.world.config.MapConfig;
import com.lance.game.demo.module.world.manager.WorldManager;
import com.lance.game.demo.module.world.model.WorldMap;
import com.lance.game.demo.module.world.model.WorldPosition;
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

    /** 地图组：地图id->地图 */
    private Map<Integer, WorldMap> worldMaps;

    @Override
    public void init() {
        // todo 我觉得大多数地图都不需要初始化，可以等到需要时再初始化。但一般都需要一个场景，所以这里暂时不考虑配置初始化
        this.worldMaps = new HashMap<>();
        for (MapConfig mapConfig : worldManager.getAllMapConfig()) {
            WorldMap worldMap = new WorldMap(mapConfig);
            worldMap.init();
            this.worldMaps.put(mapConfig.getMapId(), worldMap);
        }
    }

    @Override
    public void enterMap(Player player, int mapId) {
        WorldMap worldMap = this.worldMaps.get(mapId);
        if (worldMap == null) {
            LoggerUtil.error("玩家【{}】进入的地图【{}】不存在", player.getAccount(), mapId);
            throw new GameException(I18nId.WORLD_NOT_EXIST);
        }

        WorldPosition worldPosition = player.getWorldPosition();
        int orgMapId = worldPosition.getMapId(); // 原地图id
        WorldMap orgWorldMap = this.worldMaps.get(mapId);
        if (orgWorldMap == null) {
            LoggerUtil.error("玩家【{}】原地图【{}】不存在", player.getAccount(), orgMapId);
            throw new GameException(I18nId.WORLD_NOT_EXIST);
        }

        // 先判断能否进入新地图
        worldMap.verify(player);
        // 再离开原地图
        orgWorldMap.leave(player); // todo 需要校验能否离开当前地图，被攻击时不允许离开
        // 进入新地图
        worldMap.enter(player);
    }
}
