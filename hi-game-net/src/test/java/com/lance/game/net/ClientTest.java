package com.lance.game.net;

import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Lance
 */
public class ClientTest {

    @Test
    public void test() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8989);
        System.out.println("连接成功");

        String data = "Hello world";
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeInt(data.length());
        out.write(data.getBytes());
        out.close();
    }
}
