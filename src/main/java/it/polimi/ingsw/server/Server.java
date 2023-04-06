package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 4500;
    private static ServerSocket serverSocket;
    private static ArrayList<ServerThread> threadsList;


    public static void main(String[] args) throws IOException {
        threadsList = new ArrayList<>();

        serverSocket = new ServerSocket(PORT);

        ServerGamesData games = new ServerGamesData();

        while(true){
            Socket socket = serverSocket.accept();
            ServerThread st = new ServerThread(socket);
            threadsList.add(st);
            st.start();
        }
    }


}
