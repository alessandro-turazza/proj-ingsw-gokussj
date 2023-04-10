package it.polimi.ingsw.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ProvaS {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4500);
        Socket socket = ss.accept();

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        String s1 = "PING";
        String s;



        while((s = input.readLine())!=null) {
            System.out.println(s);
            out.println(s1);
        }

    }
}
