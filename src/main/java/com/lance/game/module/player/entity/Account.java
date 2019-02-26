package com.lance.game.module.player.entity;

import lombok.Data;

/**
 * Base game account
 *
 * @author Lance
 * @since 2018/11/10 12:36
 */
@Data
public class Account {

    private long   id;
    private String username;
    private String password;
}
