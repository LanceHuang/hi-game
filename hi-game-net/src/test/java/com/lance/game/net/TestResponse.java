package com.lance.game.net;

import com.lance.game.net.annotation.Protocol;

/**
 * @author Lance
 */
@Protocol(10087)
public class TestResponse {

    private String account;
    private int    rank;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
