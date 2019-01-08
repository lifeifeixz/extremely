/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author flysLi
 * @ClassName ExtremelyServer
 * @Decription TODO
 * @Date 2019/1/7 14:05
 * @Version 1.0
 */
public class ExtremelyServer {
    ServerSocket server = null;
    Socket socket = null;
    BufferedReader reader = null;
    PrintWriter printWriter = null;


    public ExtremelyServer() {
        try {
            server = new ServerSocket(8631);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("开始监听请求...");
        while (true) {
            try {
                socket = server.accept();
                System.out.println(socket.getInetAddress());
                new Handler(socket).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExtremelyServer server = new ExtremelyServer();
        server.run();
    }
}
