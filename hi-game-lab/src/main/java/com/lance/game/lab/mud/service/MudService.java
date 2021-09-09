package com.lance.game.lab.mud.service;

import com.lance.game.lab.mud.battle.BattleContext;
import com.lance.game.lab.mud.gameaction.GameActionType;
import com.lance.game.lab.mud.config.GameObjectConfig;
import com.lance.game.lab.mud.gameobject.GameObject;
import com.lance.game.lab.mud.identify.IIdentifyService;
import com.lance.game.lab.mud.manager.MudManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance
 * @since 2021/9/7
 */
@Service
public class MudService implements IMudService {

    @Autowired
    private IIdentifyService identifyService;

    @Autowired
    private MudManager mudManager;

    private final Map<Long, BattleContext> battleContextMap = new HashMap<>();

    @Override
    public long createBattleContext() {
        BattleContext battleContext = new BattleContext(identifyService.nextId());
        battleContextMap.put(battleContext.getId(), battleContext);
        return battleContext.getId();
    }

    @Override
    public long makeGameObject(long battleId, int configId, int x, int y) {
        long id = identifyService.nextId();
        GameObjectConfig gameObjectConfig = mudManager.getGameObjectConfig(configId);
        GameObject gameObject = gameObjectConfig.getObjectType().getGameObjectBuilder().build(configId, id);

        BattleContext battleContext = battleContextMap.get(battleId);
        battleContext.addGameObject(gameObject, x, y);
        return id;
    }

    @Override
    public void executeGameObject(long battleId, long id, GameActionType actionType, Map<String, String> params) {
        BattleContext battleContext = battleContextMap.get(battleId);
        battleContext.execute(id, actionType, params);
    }
}
