package com.lance.game.demo.module.horse.controller;

import com.lance.game.demo.module.horse.event.RideHorseEvent;
import com.lance.game.demo.module.horse.message.RideHorseRequest;
import com.lance.game.demo.module.horse.service.IHorseService;
import com.lance.game.demo.module.player.model.Player;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Controller
public class HorseController {

    @Resource
    private IHorseService horseService;

    // todo 异常处理

    // todo
    public void ride(Player player, RideHorseRequest request) {
        horseService.ride(player, request.getHorseId());
    }

    // todo
    public void onRideHorseEvent(RideHorseEvent event) {
        horseService.onRideHorseEvent(event.getPlayer(), event.getHorseId());
    }
}
