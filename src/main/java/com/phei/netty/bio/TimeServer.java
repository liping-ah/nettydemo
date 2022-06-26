package com.phei.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) {
        int port=8080;
        if (args!=null&&args.length>0){
            port=Integer.valueOf(port);

        }
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(port);
            System.out.println("The tim server is start in port "+port);
            Socket socket=null;
            while (true){
                socket =serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
