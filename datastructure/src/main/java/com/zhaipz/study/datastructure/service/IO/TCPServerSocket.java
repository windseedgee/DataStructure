package com.zhaipz.study.datastructure.service.IO;

import lombok.SneakyThrows;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhaipz
 * @ClassName: SocketTest
 * @Description: 网络编程
 * @date 2022/3/9 8:43
 */
public class TCPServerSocket implements Runnable{


    @SneakyThrows
    @Override
    public void run() {
        InetAddress ip = InetAddress.getLocalHost();
        ServerSocket socket = new ServerSocket(8090);
        Socket socket1 = socket.accept();

    }
}
