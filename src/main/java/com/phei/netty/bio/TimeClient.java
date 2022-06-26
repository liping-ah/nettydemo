package com.phei.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args) {
        Socket socket=null;
        BufferedReader in =null;
        PrintWriter out =null;
        try {
            socket=new Socket("127.0.0.1",8080);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            String resp=in.readLine();
            System.out.println("Now is  "+resp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
