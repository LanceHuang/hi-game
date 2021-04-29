package com.lance.game.net.config;

import com.lance.game.net.annotation.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Lance
 * @since 2021/4/28
 */
@Getter
@Setter
@ToString
@Message(1025)
public class User {

    private String username;
    private String password;
    private int level;
}
