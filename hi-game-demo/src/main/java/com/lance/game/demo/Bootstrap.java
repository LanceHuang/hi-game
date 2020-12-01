package com.lance.game.demo;

import com.lance.game.demo.log.LoggerUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// TODO: 2020/2/24 可以换成SpringBoot方式

/**
 * 引导工具
 *
 * @author Lance
 */
public class Bootstrap {

    private static final String APPLICATION_CONTEXT_LOCATION = "application-context.xml";
    private static final String ADMIN_CONTEXT_LOCATION       = "admin-context.xml";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide command");
            return;
        }

        String cmd = args[0];
        if ("start".equals(cmd)) {
            new Bootstrap().start();
        } else if ("stop".equals(cmd)) {
            new Bootstrap().stop();
        } else {
            System.err.println("Invalid command: " + cmd);
        }
    }

    private int port = 8080;

    public void start() {
        LoggerUtil.info("Bootstrap start");

        try {
            LoggerUtil.info("Ready to accept request");
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                LoggerUtil.info("Accept a socket: {}", socket);

                DataInputStream dIn = new DataInputStream(socket.getInputStream());
                int protocolId = dIn.readInt();
                if (protocolId == 999) {
                    LoggerUtil.info("Quit");
                }
                dIn.close();
                socket.close();
                serverSocket.close();
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_LOCATION);
//        ac.start(); // TODO: 2020/2/25
//        IGameService gameService = GameContext.getBean(IGameService.class);
//        gameService.start();
//
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                gameService.stop();
//            }
//        });
    }

    public void stop() {
        LoggerUtil.info("Bootstrap stop");
//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_LOCATION);
//        IGameService gameService = GameContext.getBean(IGameService.class);
//        gameService.stop();

        try {
            LoggerUtil.info("Ready to send request");
            Socket socket = new Socket("localhost", port);
            LoggerUtil.info("Connect completely");
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            LoggerUtil.info("Send request");
            dOut.writeInt(999);
            dOut.close();
            socket.close();
            LoggerUtil.info("Quit");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
