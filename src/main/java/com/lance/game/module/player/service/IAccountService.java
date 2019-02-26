package com.lance.game.module.player.service;

/**
 * @author Lance
 * @since 2018/11/10 14:32
 */
public interface IAccountService {

    void register(String username, String password);

    void login(String username, String password);

    void logout();
}
