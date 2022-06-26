package com.phei.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out=new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime=null;
            String body=null;
            while (true){
                body=in.readLine();
                if (body==null){
                    break;
                }
                System.out.println("This time server receive order :"+body);
                currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?
                        new java.util.Date(System.currentTimeMillis()).toString() :"badorer";

                out.println(currentTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
