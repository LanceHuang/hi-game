package com.lance.game.lab.mud.battle.action.impl;

import com.lance.game.lab.mud.battle.action.GameAction;
import com.lance.game.lab.mud.battle.BattleContext;
import com.lance.game.lab.mud.constant.MudConstant;
import com.lance.game.lab.mud.gameobject.GameObject;
import com.lance.game.lab.mud.gameobject.impl.FactoryBuilding;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 采集
 *
 * @author Lance
 * @since 2021/9/7
 */
public class GatherAction extends GameAction {

    @Override
    public void execute(BattleContext battleContext, GameObject gameObject, Map<String, String> params) {
        int objectConfigId = MapUtils.getIntValue(params, MudConstant.PARAM_OBJECT_CONFIG_ID, 0);
        if (objectConfigId == 0) {
            // todo
            return;
        }

        if (gameObject instanceof FactoryBuilding) {
            FactoryBuilding factoryBuilding = (FactoryBuilding) gameObject;
            factoryBuilding.makeGameObject(objectConfigId);
        }
    }
}
