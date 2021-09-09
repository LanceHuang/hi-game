package com.lance.game.lab.mud;

import com.lance.game.lab.mud.gameobject.BattleContext;
import com.lance.game.lab.mud.cmd.GameCommandType;
import com.lance.game.lab.mud.config.GameObjectConfig;
import com.lance.game.lab.mud.gameobject.GameObject;
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
    private MudManager mudManager;

    private final Map<Long, BattleContext> battleContextMap = new HashMap<>();

    @Override
    public long createBattleContext() {
        BattleContext battleContext = new BattleContext(nextId());
        battleContextMap.put(battleContext.getId(), battleContext);
        return battleContext.getId();
    }

    @Override
    public long makeGameObject(long battleId, int configId, int x, int y) {
        GameObjectConfig gameObjectConfig = mudManager.getGameObjectConfig(configId);
        GameObject gameObject = gameObjectConfig.getObjectType().getGameObjectBuilder().build(configId, nextId());

        BattleContext battleContext = battleContextMap.get(battleId);
        battleContext.addGameObject(gameObject, x, y);
        return gameObject.getId();
    }

    @Override
    public void executeGameObject(long battleId, long id, GameCommandType gameCommandType, Map<String, String> params) {
        BattleContext battleContext = battleContextMap.get(battleId);
        battleContext.execute(id, gameCommandType, params);
    }

    private long nextId() {
        // todo
        return 0L;
    }
}
