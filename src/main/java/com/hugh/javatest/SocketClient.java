package com.hugh.javatest;

import java.io.OutputStreamWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Created by Hugh on 2015/11/26.
 */
public class SocketClient {

    public static void main(String[] args) throws Exception {
        final String ip="localhost";
        final int port=2000;
        Socket socket = new Socket(ip, port);
        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
        String msg="";
        while(!"exit".equals(msg)) {
            out.write("Hello World��\n");
            out.flush();
            sleep(3000);
        }
        out.close();
        socket.close();
    }
}
