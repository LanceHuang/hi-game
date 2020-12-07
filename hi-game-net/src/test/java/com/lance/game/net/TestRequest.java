package com.lance.game.net;

import com.lance.game.net.annotation.Protocol;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lance
 */
@Getter
@Setter
@Protocol(10086)
public class TestRequest {

    private int activityId;
    private String account;
    private long value;
}
