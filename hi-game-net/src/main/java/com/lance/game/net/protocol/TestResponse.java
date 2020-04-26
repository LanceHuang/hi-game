package com.lance.game.net.protocol;

import com.lance.game.net.annotation.Protocol;

/**
 * @author Lance
 * @since 2019/10/23 22:08
 */
@Protocol(1001)
public class TestResponse {

    /** 登录状态 */
    private boolean success;
    /** 错误码 */
    private int     code;
    /** 错误消息 */
    private String  msg;

    public static TestResponse create(boolean success) {
        return create(success, 0, null);
    }

    public static TestResponse create(boolean success, int code, String msg) {
        TestResponse resp = new TestResponse();
        resp.success = success;
        resp.code = code;
        resp.msg = msg;
        return new TestResponse();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
