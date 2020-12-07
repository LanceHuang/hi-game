package com.lance.game.net;

import com.lance.game.net.annotation.Protocol;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lance
 */
@Getter
@Setter
@Protocol(10087)
public class TestResponse {

    private String account;
    private int rank;
}
