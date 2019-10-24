package com.lance.net.protocol;

import com.lance.net.annotation.Protocol;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/10/23 22:08
 */
@Protocol(1001)
@Data
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
}
