package it.polimi.ingsw.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionDeamon extends Thread{ //respond to the server's check for the connection

    private static final int READ_PORT=50001;
    private static final int WRITE_PORT=50002;
    private static String ipServer;
    private static Socket readSocket;
    private static Socket writeSocket;

    public static void setIpServer(String ipServer) {
        ConnectionDeamon.ipServer = ipServer;
    }

    @Override
    public void run() {
        try {

            readSocket = new Socket(ipServer, READ_PORT);
            writeSocket =new Socket(ipServer, WRITE_PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(readSocket.getInputStream()));
            PrintWriter out = new PrintWriter(writeSocket.getOutputStream(), true);
            String response;
            do{
                response = input.readLine();
                if(response!=null) {
                   out.println("CONNECTED");
                }
            }while(true);
        }catch (Exception e){throw new RuntimeException();}

    }
}
