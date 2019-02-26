package com.lance.game.module.player.controller;

import com.lance.game.module.player.service.IAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2018/11/10 14:45
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private IAccountService accountService;

    //todo

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String username, String password) {

    }

}
