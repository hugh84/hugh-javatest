package com.hugh.javatest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hugh on 2015/11/26.
 */
public class SocketServer {
    public static void main(String[] args) throws Exception {
        int port=2000;
        System.out.println("���������,�����˿�:"+port);
        ServerSocket server=new ServerSocket(port);
        Socket socket=server.accept();
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg="";
        while(!"exit".equals(msg)){
            msg=in.readLine();
            System.out.println("received:"+msg);
        }
        in.close();
        socket.close();
        server.close();
        System.out.println("������˳�.");
    }
}
