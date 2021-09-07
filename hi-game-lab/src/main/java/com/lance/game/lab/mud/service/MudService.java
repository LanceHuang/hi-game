package com.lance.game.lab.mud.service;

import com.lance.game.lab.mud.battle.action.GameActionType;
import com.lance.game.lab.mud.identify.IIdentifyService;
import com.lance.game.lab.mud.manager.MudManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Lance
 * @since 2021/9/7
 */
@Service
public class MudService implements IMudService {

    @Autowired
    private IIdentifyService iIdentifyService;

    @Autowired
    private MudManager mudManager;

    @Override
    public long createBattleContext() {

        return 0;
    }

    @Override
    public long makeGameObject(long battleId, int configId, int x, int y) {
        return 0;
    }

    @Override
    public void executeGameObject(long id, GameActionType actionType, Map<String, String> params) {

    }
}
