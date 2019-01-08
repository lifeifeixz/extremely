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

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName Handler
 * @Decription TODO
 * @Date 2019/1/7 14:09
 * @Version 1.0
 */
public class Handler implements Runnable {
    Socket socket = null;
    ExecuteEngine executeEngine = new ExecuteEngine();

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[2048];
            inputStream.read(data);
            String str = new String(data);
            System.out.println("客户端消息:" + str);
//            List<Map<String, Object>> result = new ArrayList<>();
//            Map<String, Object> objectMap = new HashMap<>(1);
//            objectMap.put("name", "new");
//            result.add(objectMap);
            Object result = executeEngine.execute(str);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            printWriter.flush();
            System.out.println("消息已经返回给客户端!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
