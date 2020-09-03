package com.lance.game.demo.module.horse.service;

import com.lance.game.demo.log.LogCode;
import com.lance.game.demo.log.LogModule;
import com.lance.game.demo.log.LoggerUtil;
import com.lance.game.demo.module.horse.manager.HorseManager;
import com.lance.game.demo.module.player.model.Player;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Service
public class HorseService implements IHorseService {

    @Resource
    private HorseManager horseManager;

    @Override
    public void ride(Player player, int horseId) {
        LoggerUtil.log(LogModule.HORSE, LogCode.HORSE_RIDE, "account:{},horseId:{} 上坐骑", player.getAccount(), horseId);
        // todo 抛出事件
    }

    @Override
    public void onRideHorseEvent(Player player, int horseId) {
        LoggerUtil.info("account:{},horseId:{} 处理上坐骑事件", player.getAccount(), horseId);
    }
}
