package com.lance.game.demo.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 校验结果
 *
 * @author Lance
 */
@Getter
@Setter
public class VerifyResult {

    /** 校验结果 */
    private boolean success = true;

    /** 错误码 */
    private int errorCode;

    /**
     * 校验失败
     *
     * @param errorCode 错误码
     */
    public void fail(int errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }
}
