package com.lance.game.demo.core.condition;

/**
 * 校验结果
 *
 * @author Lance
 */
public class VerifyResult {

    /** 校验结果 */
    private boolean success = true;

    /** 错误码 */
    private int errorCode;

    public void fail(int errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
