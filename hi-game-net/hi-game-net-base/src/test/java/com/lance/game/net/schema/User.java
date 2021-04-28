package com.lance.game.net.schema;

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
public class User {

    private String username;
    private String password;
    private int level;
}
