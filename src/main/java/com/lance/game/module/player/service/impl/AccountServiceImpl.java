package com.lance.game.module.player.service.impl;

import com.lance.game.module.player.manager.AccountManager;
import com.lance.game.module.player.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2018/11/10 14:40
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Resource
    private AccountManager accountManager;

    //todo
}
