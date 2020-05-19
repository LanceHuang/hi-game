package com.lance.game.net;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BioGameServerTest {

    private BioGameServer gameServer;

    @BeforeEach
    public void beforeEach() {
        this.gameServer = new BioGameServer();
        this.gameServer.setPort(8989);
    }

    @Test
    public void test() {
        this.gameServer.startup();
    }
}