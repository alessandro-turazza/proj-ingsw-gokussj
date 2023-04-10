package it.polimi.ingsw.client;

import java.io.*;
import java.net.Socket;

public class Prova {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",4500);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        String s1 = "PONG";
        String s;

        out.println(s1);

       while((s = input.readLine())!= null){
            System.out.println(s);
            out.println(s1);
        }
    }
}
