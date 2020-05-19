package com.lance.game.net;

import com.lance.game.net.annotation.Protocol;

/**
 * @author Lance
 */
@Protocol(10086)
public class TestRequest {

    private int    activityId;
    private String account;
    private long   value;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TestRequest{" +
                "activityId=" + activityId +
                ", account='" + account + '\'' +
                ", value=" + value +
                '}';
    }
}
