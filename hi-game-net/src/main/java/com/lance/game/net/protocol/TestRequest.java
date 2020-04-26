package com.lance.game.net.protocol;

import com.lance.game.net.annotation.Protocol;

/**
 * @author Lance
 * @since 2019/10/23 22:07
 */
@Protocol(1000)
public class TestRequest {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
